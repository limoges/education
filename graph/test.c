#include <stdio.h>

#include "utils.c"

#define BUFLEN 100

int main()
{
    FILE * file = fopen("matrix", "r");
    char buffer[BUFLEN], buffer2[BUFLEN];
    int result = 0, count = 0;
    while (getline(buffer, BUFLEN, file))
    {
        printf("%s\n", buffer);
        getnexti(buffer, ':', &result);
        printf(":[%d]", result);
        while (getnexti(NULL, ';', &result))
        {
            printf(";[%d]", result);
        }
    }

}
