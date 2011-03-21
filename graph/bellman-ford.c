#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

#include "utils.c"

#define BUFLEN 100

void bellman_ford(int ** vertices, int size/*, int source*/)
{
    int source = 0;
    int * distance = malloc(size * sizeof(int));
    int x, y;
    // STEP 1: Initialize the graph
    for (x = 0; x < size; x++)
    {
        if (x == source) distance[x] = 0;
        else distance[x] = INT_MAX;
        // x.predecessor = NULL
    }
    // STEP 2: Relax the edges repeatedly
    for (x = 0; x < size; x++)
    {
        if (x == source) continue; // we don't need to reevaluate
        for (y = 0; y < size; y++)
        {
            //if (distance[y] + distance[x][y] < distance[y])
            //if (distance[x] + 
        }
    }
}

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
            vertices[x][y] = 0;
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

    bellman_ford(vertices, size);
    return EXIT_SUCCESS;
}
