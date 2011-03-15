#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h> // memset
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> // for close(socket)

#include <time.h>
#include "defs.h"

char * word[] = {   "dexter morgan",
                    "morgan freeman",
                    "iris boboc",
                    "thomas glover",
                    "thomas lewis",
                    "katie cook",
                    "marshall ericsson",
                    "flora desquirez",
                    "marieve rozon",
                    "vincent gingras",
                    "sylvain polak",
                    "john nolan",
                    "thomas keavon"
                };

int main()
{
    int sock;// socket descriptor
    struct sockaddr_in sockadd;// socket address
    int send_to;// sendto function's result
    char buffer[200];//buffer to put the message
    srand(time(NULL));
    int rdm;
    
    sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);
    
    while (strcmp(buffer, "exit\n") != 0 )
    {
        sleep(1);
        //fgets(buffer, 250, stdin);
        rdm = rand() % 6 + 1;
        strcpy(buffer, word[rdm]);
        printf("Sending [%s]...", buffer);
        if (sock < 0)
        {
            perror("could not create the socket");
            exit(EXIT_FAILURE);
        }

        memset(&sockadd, 0, sizeof(sockadd));

        sockadd.sin_family = AF_INET;
        sockadd.sin_addr.s_addr = inet_addr("127.0.0.1");
        sockadd.sin_port = htons(PORT);

        send_to = sendto(
                sock, buffer, sizeof(buffer), 0,
                (struct sockaddr*) &sockadd, sizeof(sockadd)
        );
        printf(" sent.\n");
        if (send_to < 0)
        {
            perror("sendto error");
        }
    }
    close(sock);

    exit(EXIT_SUCCESS);
}
