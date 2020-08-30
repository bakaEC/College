#include<stdio.h>
#include<stdint.h>
#include<stddef.h>
#include<stdlib.h>
#include<signal.h>
#include<winsock2.h>

#define MAX_PEERS 256
#define PORT_NUMBER 25567

int ListenerSocket = INVALID_SOCKET;
size_t Peers[MAX_PEERS];
size_t numPeers=0;

int ServerShouldGoDie = 0;

void sigproc(int sig){
    switch (sig)
    {
    case SIGINT:
    case SIGTERM:
        ServerShouldGoDie = 1;
        break;
    }
    
}


int main(int argc, char const *argv[])
{
    signal(SIGINT,sigproc);
    signal(SIGTERM,sigproc);
    WSADATA wsaData;
    WSAStartup(WINSOCK_VERSION,&wsaData);
    ListenerSocket=socket(AF_INET,SOCK_STREAM,IPPROTO_TCP);
    if (ListenerSocket == INVALID_SOCKET)   
    {
        fprintf(stderr,"创建侦听TCP端口用的socket\n");
        goto FailExit;
    }
    SOCKADDR_IN BindAddr;
    memset(&BindAddr,0,sizeof BindAddr);
    BindAddr.sin_family=AF_INET;
    BindAddr.sin_port=htons(PORT_NUMBER);
    BindAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    if (bind(ListenerSocket,(SOCKADDR*)&BindAddr,sizeof BindAddr)==SOCKET_ERROR)
    {
        fprintf(stderr,"绑定TCP端口 %u 失败了，错误代码 %d\n",PORT_NUMBER,h_errno);
        goto FailExit;
    }

    if(listen(ListenerSocket,MAX_PEERS)==SOCKET_ERROR){
        fprintf(stderr,"绑定TCP端口 %u 失败了，错误代码 %d\n",PORT_NUMBER,h_errno);
        goto FailExit;
    }
    

    closesocket(ListenerSocket);
    WSACleanup();
    return 0;

FailExit:
    if (ListenerSocket != INVALID_SOCKET) closesocket(ListenerSocket){
    WSACleanup();
    return 1;
    }
    

    
}
