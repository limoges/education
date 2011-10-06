#include <stdlib.h>
#include <stdio.h>

#include "utils.c"
#include "limits.h"

#define BUFLEN 100
#define INFINITY INT_MAX
#define SAY(x) printf(x);fflush(stdout)

int djikstra(int ** vertices, int size, int start, int end);
void djikstra_fix(int ** vertices, int ** path, int size);
int explore(int * vertices, int size);

int main(int argc, char ** argv)
{
    char filename[] = "matrix";
    FILE * file = fopen(filename, "r");
    if (file == NULL)
    {
        printf("Couldn't open \'%s\'.", filename);
        perror("fopen");
        exit(EXIT_FAILURE);
    }
	
    int start, end, x, y, size = countl(file);
    int ** matrix = malloc(size * sizeof(int *));
    for (x = 0; x < size; x++)
    {
        matrix[x] = malloc(size * sizeof(int));
        for (y = 0; y < size; y++)
            matrix[x][y] = 0;
    }
	
    char line[BUFLEN];
    int weight, result;
    while (getline(line, BUFLEN, file))
    {
        getnexti(line, ':', &x);
        do
        {
            result = getnexti(NULL, '.', &y);
            if (!result) break;
            result = getnexti(NULL, ';', &weight);
            matrix[x-1][y-1] = weight;
        }
        while (result);
    }
    fclose(file);

    djikstra(matrix, size, 0, 5);
    exit(EXIT_SUCCESS);
}

int djikstra(int ** vertices, int size, int start, int end)
{
    //printf("int djikstra( [%p], [%d], [%d], [%d]);", vertices, size, start, end);
    int * explored = malloc(size * sizeof(int));
    int * distance = malloc(size * sizeof(int));
    int ** path = malloc(size * sizeof(int *));
    int x, y, current;
    for (x = 0; x < size; x++)
    {
        path[x] = malloc(size * sizeof(int));
        for (y = 0; y < size; y++)
            path[x][y] = -1;
    }
    djikstra_fix(vertices, path, size);
    for (x = 0; x < size; x++)
    {
        explored[x] = 0; // unexplored
        distance[x] = INFINITY;
    }
    print(vertices, size, "vertices");
    printl(explored, size, "explored");
    printl(distance, size, "distance");
    int ishortest, shortest, curdis, newdis;
    current = start;
    explored[start] = 1;
    distance[start] = 0;
    ishortest = start;    
    while (!explore(explored, size))
    {
        shortest = INFINITY;
        curdis = distance[current];
        for (x = 0; x < size; x++)
        {
            if (!explored[x])
            {
                if (vertices[current][x] == INFINITY)
                    newdis = INFINITY;
                else
                    newdis = curdis + vertices[current][x];
                if (newdis < distance[x])
                    distance[x] = newdis;
                if (distance[x] < shortest)
                {
                    shortest = distance[x];
                    ishortest = x;
                }
            }
        }
        current = ishortest;
        explored[current] = 1;
        printl(distance, size, "distance");
    }
    print(path, size, "path");
}

int explore(int * vertices, int size)
{
    int x;
    for (x = 0; x < size; x++)
    {
        if (!vertices[x])
            return 0;
    }
    return 1;
}

void djikstra_fix(int ** vertices, int ** path, int size)
{
    int x, y;
    for (x = 0; x < size; x++)
    {
        for (y = 0; y < size; y++)
        {
            /*if (x == y) vertices[x][y] = 0;
			 else*/
            if (vertices[x][y] == 0)
                vertices[x][y] = INFINITY;
            if (x == y || vertices[x][y] != INFINITY);
			path[x][y] = x;
        }
    }
}
