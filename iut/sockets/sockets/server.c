#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdlib.h>

#include "defs.h"

#define BUFSIZE 100

int main()
{
    struct sockaddr_in sockaddr;
    char buffer[BUFSIZE];
    int result;
    socklen_t socklen;
    int sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);
    if (sock < 0)
    {
        perror("error creating the socket");
        exit(EXIT_FAILURE);
    }

    memset(&sockaddr, 0, sizeof(sockaddr));

    sockaddr.sin_family = AF_INET;
    sockaddr.sin_addr.s_addr = INADDR_ANY;
    sockaddr.sin_port = htons(PORT);

    socklen = sizeof(sockaddr);
    if (bind(sock, (struct sockaddr *) &sockaddr, sizeof(sockaddr)) < 0)
    {
        perror("bind failed");
        close(sock);
        exit(EXIT_FAILURE);
    }
    printf("before...");
    while (strcmp(buffer, "exit\n") != 0)
    {
        printf("Listening...");
        result = recvfrom(sock, buffer, sizeof(buffer), 0, (struct sockaddr*) &sockaddr, &socklen);
        if (result < 0)
        {
            perror("recvfrom error");
        }
        printf("[%s] received.", buffer);
    }
    close(sock);
    exit(EXIT_SUCCESS);
}
