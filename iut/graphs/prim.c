#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

#include "utils.c"

#define BUFLEN 100

int main(int argc, char ** argv)
{
    char filename[] = "matrix";
    FILE * file = fopen(filename, "r");
    ptrtest(filename, "fopen");
    
    int size = countl(file), x, y;
    int ** vertices = malloc(size * sizeof(int *));
    for (x = 0; x < size; x++)
    {
        vertices[x] = malloc(size * sizeof(int));
        for (y = 0; y < size; y++)
        {
            if (x == y)
                vertices[x][y] = 0;
            else
                vertices[x][y] = INT_MAX;
        }
    }

    int result, weight;
    char buffer[BUFLEN];
    while (getline(buffer, BUFLEN, file))
    {
        getnexti(buffer, ':', &x);
        do
        {
            result = getnexti(NULL, '.', &y);
            if (!result)
                break;
            result = getnexti(NULL, ';', &weight);
            vertices[x-1][y-1] = weight;
        }
        while (result);
    }
    fclose(file);

    print(vertices, size, "vertices");
    return EXIT_SUCCESS;
}
