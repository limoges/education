#include <stdlib.h>
#include <stdio.h> // FILE

#ifndef __UTILS__C__LIMOGES__
#define __UTILS__C__LIMOGES__


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

/*
 * getnexti
 * get the next integer before the delimiter
 * [Successful conversion] int value
 * [Failed] zero value
 * [Out of range] INT_MAX or INT_MIN
 */
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

#endif


