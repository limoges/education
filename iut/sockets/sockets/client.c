#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h> // memset
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> // for close(socket)

#include <time.h>

const int DEFAULT_PORT = 2011;

char * word[] = {   "allo",
                    "shaft",
                    "nipples",
                    "gros seins",
                    "chanteur",
                    "belle fesses",
                    "voleur",
                    "tout nu",
                    "odeur de cul",
                    "ets",
                    "chinook",
                    "badaboom",
                    "winner winner chicken dinner"
                };

int main()
{
    int sockfd;
    struct sockaddr_in addr;
    int send_to;
    char buffer[1024];
    srand(time(NULL));
    int n;
    
    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) <0)
    {
        perror("Unable to create the socket");
        exit(EXIT_FAILURE); /* TODO define error codes */
    }
    //while (strcmp(buffer, "exit\n") != 0 )
    //{
        //sleep(1);
        //fgets(buffer, 250, stdin);
        //rd = rand() % 6 + 1;
        //strcpy(buffer, word[rd]);
        //printf("Sending [%s]...", buffer);

        memset(&addr, 0, sizeof(addr));

        addr.sin_family = AF_INET;
        addr.sin_addr.s_addr = inet_addr("24.203.132.175");
        addr.sin_port = htons(DEFAULT_PORT);

        if ((connect(sockfd, (struct sockaddr *)&addr, sizeof(addr))) != 0)
        {
            perror("Connection attempt failed");
        }

        while (1)
        {
        printf("julien: ");
        bzero(buffer, 1024);
        fgets(buffer, 1024, stdin);
        n = write(sockfd, buffer, strlen(buffer));
        if (n < 0)
            perror("Error writing to socket");
        //send_to = sendto(sockfd, buffer, sizeof(buffer), 0, (struct sockaddr*) &addr, sizeof(addr));
        //}
        }
    //}
    close(sockfd);

    exit(EXIT_SUCCESS);
}
