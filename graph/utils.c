#include <stdlib.h>
#include <stdio.h> // FILE
#include <limits.h>

#ifndef __UTILS__C__LIMOGES__
#define __UTILS__C__LIMOGES__
inline void ptrtest(void * ptr, char * message)
{
    if (ptr == NULL)
    {
        perror(message);
        exit(EXIT_FAILURE);
    }
}

static int getline(char * s, int n, FILE * f)
{
    register int nMinus1 = (n-1), i = 0, ch;
    while (1)
    {
        ch = (char) getc(f);
        if (ch == '\r')
            ch = getc(f);
        if ((ch == '\n') || (ch == EOF) || (i == nMinus1))
        {
            *s = '\0';
            return (feof(f) ? 0 : 1);
        }
        else *s++ = ch;
        ++i;
    }
}

static int getnexti(char * const s, const char delim, int * const result) 
{
    static char * start, * ch;
    if (s != NULL)
        start = s;
    char temp;
    int i = 0;
    ch = start;
    while (*ch != '\0') // while we don't hit the end of the string
    {
        if (*ch == delim) // if we hit the delimiter
        {
            temp = *ch;
            *ch = '\0';
            *result = atoi(start);
            *ch = temp;
            start = ++ch;
            return 1;
        }
        i++;
        ch++;
    }
    return 0;
}

static int countl(FILE * file)
{
    int c, n = 0;
    do
    {
        c = fgetc(file);
        if (c == 10 || c == 13 || (c == 13 && c+1 == 10)) n++;
    }
    while (c != EOF);
    fseek(file, 0, SEEK_SET);
    return n;
}

void printl(int * vertices, int size, char * name) 
{                                                  
    int x;                                         
    for (x = 0; x < size; x++)                     
    {                                              
        if (vertices[x] == INT_MAX)                
            printf("[i+]");                        
        else if (vertices[x] == -1)                
            printf("[--]");                        
        else if (vertices[x] < 10)                 
            printf("[ %d]", vertices[x]);          
        else if (vertices[x] > 99)                 
            printf("[++]");                        
        else                                       
            printf("[%d]", vertices[x]);           
    }                                              
    printf(" %s\n", name);                         
}                                                  

void print(int ** vertices, int size, char * name)
{
    int x = 0;
    printl(vertices[x], size, name);
    for (x = 1; x < size; x++)
        printl(vertices[x], size, "...");
}
#endif
