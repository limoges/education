#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h> // memset
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> // for close(socket)

#include "defs.h"

int main()
{
    int sock;// socket descriptor
    struct sockaddr_in sockadd;// socket address
    int send_to;// sendto function's result
    char buffer[100];//buffer to put the message

    while (strcmp(buffer, "exit\n") != 0 )
    {
        fgets(buffer, 250, stdin);
        
        sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);

        if (sock < 0)
        {
            perror("could not create the socket");
            exit(EXIT_FAILURE);
        }

        memset(&sockadd, 0, sizeof(sockadd));

        sockadd.sin_family = AF_INET;
        sockadd.sin_addr.s_addr = inet_addr("127.0.0.1");
        sockadd.sin_port = htons(PORT);

        send_to = sendto(sock, buffer, sizeof(buffer), 0, (struct sockaddr*) &sockadd, sizeof(sockadd));
        if (send_to < 0)
        {
            perror("sendto error");
        }
    }
    close(sock);

    exit(EXIT_SUCCESS);
}
