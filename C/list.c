#include<stdio.h>
#include<winsock2.h>

int main(int argc, char const *argv[])
{
    WSADATA wsaData;
    WSAStartup(WINSOCK_VERSION,&wsaData);
    printf("hell world");
    WSACleanup;
    return 0;
}
