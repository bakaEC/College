#include<stdio.h>
#include<conio.h>
#include<stdint.h>
#include<stddef.h>
#include<stdlib.h>
#include<signal.h>
#include<winsock2.h>
#include<WS2tcpip.h>
#include<Windows.h>

// 最大接受的连接数量
#define MAX_PEERS 256

// 端口号
#define PORT_NUMBER 25567

// 每次收发的最大数据量
#define BUFFER_SIZE 8192

// 为了调用select函数，我们需要设置这个宏用于做一个大小合适的fd_set
// 其中fd_set是一个结构体，本质上是用来代表一种数组
#define FD_SETSIZE (MAX_PEERS + 1)

// 侦听专用socket
int ListenerSocket = INVALID_SOCKET;

// 传入连接Socket数组
int Peers[MAX_PEERS];
size_t numPeers = 0; // 连接的数量

// 服务端是否应该退出
volatile int ProgramShouldGoDie = 0;

// 自己的信号处理函数
void sigproc(int sig)
{
	// 判断信号，处理信号
	switch (sig)
	{
	case SIGINT:
	case SIGTERM:
		ProgramShouldGoDie = 1;
		break;
	}
}

int ServerWork()
{
	// 创建侦听TCP端口用的Socket
	ListenerSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (ListenerSocket == INVALID_SOCKET)
	{
		fprintf(stderr, "创建侦听TCP端口用的Socket失败了。\n");
		goto FailExit;
	}

	// 绑定TCP端口用于侦听
	SOCKADDR_IN BindAddr;
	memset(&BindAddr, 0, sizeof BindAddr);
	BindAddr.sin_family = AF_INET;
	BindAddr.sin_port = htons(PORT_NUMBER); // 端口号
	BindAddr.sin_addr.s_addr = htonl(INADDR_ANY); // 要绑定的地址，INADDR_ANY意思是从任何地址传入的连接都可以
	if (bind(ListenerSocket, (SOCKADDR*)&BindAddr, sizeof BindAddr) == SOCKET_ERROR)
	{
		fprintf(stderr, "绑定TCP端口 %u 失败了，错误代码%d\n", PORT_NUMBER, h_errno);
		goto FailExit;
	}

	// 开始侦听
	if (listen(ListenerSocket, MAX_PEERS) == SOCKET_ERROR)
	{
		fprintf(stderr, "侦听TCP端口 %u 失败了，错误代码%d\n", PORT_NUMBER, h_errno);
		goto FailExit;
	}

	printf("服务端已启动，等待传入连接\n");

	// 设置信号
	signal(SIGINT, sigproc);
	signal(SIGTERM, sigproc);

	// 接下来进入服务器循环。
	// 所谓服务器循环，就是这个服务器要不断地处理这个循环，从而实现不停地处理客户端的请求。
	while (!ProgramShouldGoDie)
	{
		size_t i;

		// 可读的套接字数组
		// 注意，传入连接的发生，也是一种可读
		fd_set readfds;

		// 可写的套接字数组
		fd_set writefds;

		// select()函数的等待时间
		struct timeval check_time;
		check_time.tv_sec = 1;
		check_time.tv_usec = 0;

		// 清零这些套接字数组
		FD_ZERO(&readfds);
		FD_ZERO(&writefds);

		// 装入我们想要用来判断的套接字数组
		for (i = 0; i < numPeers; i++)
		{
			// 一边写入套接字到这些数组里，一边更新数组的数量计数
			readfds.fd_array[readfds.fd_count++] = Peers[i];
			writefds.fd_array[writefds.fd_count++] = Peers[i];
		}

		// 并在最后把我们的侦听套接字也加入到readfds
		if (numPeers < MAX_PEERS)
		{
			readfds.fd_array[readfds.fd_count++] = ListenerSocket;
		}

		// 接下来就是处理所有套接字的时刻——使用select()函数
		// 这个函数可以在没有任何请求的时候等待（休息）并释放CPU
		// 一旦产生了请求，它会把请求汇总起来，然后返回。
		// 有了请求，你就需要处理请求。
		if (select(FD_SETSIZE + 1, &readfds, &writefds, NULL, &check_time) < 0)
		{
			// 返回值小于零意味着出错了，直接退出
			fprintf(stderr, "select()函数失败了，错误代码%d\n", h_errno);
			goto FailExit;
		}

		// 接下来我们就知道有哪些套接字可读，哪些套接字可写了
		for (i = 0; i < readfds.fd_count; i++)
		{
			int CurSocket = readfds.fd_array[i];
			// 因为可读的套接字数组里混入了一个监听套接字，所以要单独处理
			if (CurSocket == ListenerSocket)
			{
				SOCKADDR ClientAddr;
				int AddrLen = sizeof ClientAddr;
				int Client = accept(ListenerSocket, &ClientAddr, &AddrLen);
				if (Client == INVALID_SOCKET)
				{
					fprintf(stderr, "尝试接收连接时发生错误，错误代码%d\n", h_errno);
				}
				else
				{
					// 将客户端加入到数组里
					Peers[numPeers++] = Client;

					// 判断客户端的地址是不是一个IPv4地址，是的话显示一下地址
					if (ClientAddr.sa_family == AF_INET)
					{
						SOCKADDR_IN* AddrIn = (SOCKADDR_IN*)&ClientAddr;
						printf("客户端 %s 加入服务器。\n", inet_ntoa(AddrIn->sin_addr));
					}
					else // 客户端不是一个IPv4地址
					{
						printf("客户端加入服务器。\n");
					}
				}
			}
			else
			{
				// 客户端发来了数据，接收，并广播
				// 使用缓冲区接收客户端发来的数据
				size_t j;
				char Buffer[BUFFER_SIZE];
				int ReceivedLen = recv(CurSocket, Buffer, sizeof Buffer, 0);
				if (ReceivedLen > 0)
				{
					// 接收到了数据，我们将其显示一下，并广播出去
					fwrite(Buffer, ReceivedLen, 1, stdout);
					for (j = 0; j < numPeers; j++)
					{
						// 不管对方是否可写，直接写。这是一个简单的例子，此处不考虑DOS攻击防御。
						if (Peers[j] != CurSocket) // 确保是发给别人不是发给自己
						{
							send(Peers[j], Buffer, ReceivedLen, 0);
						}
					}
				}
				else
				{
					// 套接字关闭
					// 先关闭这个套接字，然后把数组最尾部那个套接字放到当前位置，最后数组大小减一。
					closesocket(CurSocket);

					// 找到那个被关闭的套接字
					for (j = 0; j < numPeers; j++)
					{
						if (Peers[j] == CurSocket)
						{
							Peers[j] = Peers[--numPeers];
							break;
						}
					}
				}
			}
		}
	}

	closesocket(ListenerSocket);
	return 0;
FailExit:
	if (ListenerSocket != INVALID_SOCKET) closesocket(ListenerSocket);
	return 1;
}

static DWORD WINAPI ClientConsoleInputThread(void* Param);

int ClientWork()
{
	// 客户端套接字
	int ClientSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (ClientSocket == INVALID_SOCKET)
	{
		fprintf(stderr, "创建客户端Socket失败了。\n");
		goto FailExit;
	}

	// 用户输入的地址（可以是域名，可以是IP地址）
	char ServerAddressString[256];

	// 端口号的字符串
	char PortNum[8];

	// 域名解析相关
	struct addrinfo AddrHints;
	struct addrinfo* pAddrInfoFirst = NULL;
	struct addrinfo* pAddrInfo; // 用于走链表

	printf("请输入服务器地址：\n");
	scanf("%s", ServerAddressString);
	sprintf(PortNum, "%d", PORT_NUMBER);

	memset(&AddrHints, 0, sizeof AddrHints);
	AddrHints.ai_flags = 0;
	AddrHints.ai_family = AF_INET;
	AddrHints.ai_socktype = SOCK_STREAM;
	AddrHints.ai_protocol = IPPROTO_TCP;
	AddrHints.ai_addrlen = 0;
	AddrHints.ai_canonname = NULL;
	AddrHints.ai_addr = NULL;
	AddrHints.ai_next = NULL;

	getaddrinfo(ServerAddressString, PortNum, &AddrHints, &pAddrInfoFirst);

	//遍历列表直到能连接。
	pAddrInfo = pAddrInfoFirst;
	do
	{
		printf("正在连接服务器\n");
		if (!connect(ClientSocket, pAddrInfo->ai_addr, (int)(pAddrInfo->ai_addrlen)))
		{
			printf("已连接服务器。\n");
			break;//连接上了就跳出循环
		}
		pAddrInfo = pAddrInfo->ai_next;//寻到链表下一个
	} while (pAddrInfo);

	if (!pAddrInfo) //链表到头了
	{
		fprintf(stderr, "无法解析的IP地址：%s\n", ServerAddressString);
		goto FailExit;
	}

	//释放整个链表
	freeaddrinfo(pAddrInfoFirst);

	// 设置信号
	signal(SIGINT, sigproc);
	signal(SIGTERM, sigproc);

	DWORD dwThreadId;
	CloseHandle(CreateThread(NULL, 0, ClientConsoleInputThread, &ClientSocket, 0, &dwThreadId));
	while (!ProgramShouldGoDie)
	{
		char Buffer[BUFFER_SIZE];
		int ReceivedBytes = recv(ClientSocket, Buffer, sizeof Buffer, 0);
		if (ReceivedBytes > 0)
		{
			fwrite(Buffer, ReceivedBytes, 1, stdout);
		}
		else
		{
			ProgramShouldGoDie = 1;
		}
	}

	closesocket(ClientSocket);
	return 0;
FailExit:
	if(pAddrInfoFirst) freeaddrinfo(pAddrInfoFirst);
	if(ClientSocket != INVALID_SOCKET) closesocket(ClientSocket);
	return 1;
}

static DWORD WINAPI ClientConsoleInputThread(void* Param)
{
	int ClientSocket = *(int*)Param;
	char SendBuffer[BUFFER_SIZE];
	while (!ProgramShouldGoDie)
	{
		fgets(SendBuffer, sizeof SendBuffer, stdin);
		if (send(ClientSocket, SendBuffer, strlen(SendBuffer), 0) <= 0) break;
	}
}

int main(int argc, char **argv)
{
	int retval = 0;

	// 初始化WSA
	WSADATA wsaData;
	WSAStartup(WINSOCK_VERSION, &wsaData);

	if (argc > 1) // 有参数，以服务器的方式工作
	{
		retval = ServerWork();
	}
	else // 没参数，以客户端的方式工作
	{
		retval = ClientWork();
	}

	WSACleanup();
	return retval;
}