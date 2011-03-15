#include <stdlib.h>
#include <stdio.h>

#include "getline.c"
#include "getnexti.c"
#include "countl.c"
#include "graphs.h"
#include "limits.h"

#define BUFLEN 100
#define INFINITY INT_MAX

int djikstra(int ** vertices, int size, int start, int end);
void fix(int ** vertices, int ** path, int size);
void printl(int * vertices, int size);
int explore(int * vertices, int size);

int main()
{
    FILE * file = fopen("graph1", "r");
    FILE * stdo = fdopen(0, "rw");
    if (file == NULL)
    {
        perror("fopen");
        exit(EXIT_FAILURE);
    }
	
    int start, end, x, y, matsize = countl(file);
    fseek(file, 0, SEEK_SET);
    int ** matrix = malloc(matsize * sizeof(int *));
    for (x = 0; x < matsize; x++)
    {
        matrix[x] = malloc(matsize * sizeof(int));
        for (y = 0; y < matsize; y++)
            matrix[x][y] = 0;
    }
	
    char line[BUFLEN];
    int id, weight, ret, curid;
    while (getline(line, BUFLEN, file))
    {
        getnexti(line, ':', &curid);
        do
        {
            ret = getnexti(NULL, '.', &id);
            if (!ret) break;
            ret = getnexti(NULL, ';', &weight);
            //if (weight == 0)
            matrix[curid-1][id-1] = weight;
            //printf("matrix[%d][%d] = %d;\n", curid-1, id-1, weight);
        }
        while (ret);
    }
    fclose(file);
	
    djikstra(matrix, matsize, 0, 5);
    exit(EXIT_SUCCESS);
}

int djikstra(int ** vertices, int size, int start, int end)
{
    //printf("int djikstra( [%p], [%d], [%d], [%d]);", vertices, size, start, end);
    int * explored = malloc(size * sizeof(int)); // explored vertices
    int * distance = malloc(size * sizeof(int)); // shortest distance from the start
    int ** path = malloc(size * sizeof(int *));
    int x, y, current;
    for (x = 0; x < size; x++)
    {
        path[x] = malloc(size * sizeof(int));
        for (y = 0; y < size; y++)
            path[x][y] = -1;
    }
    fix(vertices, path, size);
    for (x = 0; x < size; x++)
    {
        explored[x] = 0; // unexplored
        distance[x] = INFINITY;
    }
    // Print vertices
    printf("----\nVERTICES\n");
    for (x = 0; x < size; x++)
    {
        printl(vertices[x], size);
        printf("\n");
    }
    printf("----\nEXPLORED ");
    printl(explored, size);
    printf("\n----\nDISTANCE ");
    printl(distance, size);
    printf("\n----\n");
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
                // to prevent integer wrap around.
                if (vertices[current][x] == INFINITY)
                    newdis = INFINITY;
                else
                {
                    newdis = curdis + vertices[current][x];
                    //path[current][x] = current;
                }
                if (newdis < distance[x])
                {
                    distance[x] = newdis;
					//path[current][x] = x;
                }
                if (distance[x] < shortest)
                {
                    shortest = distance[x];
                    ishortest = x;
                }
            }
        }
        current = ishortest;
        explored[current] = 1;
        printl(distance, size);
        printf(" {%d}", current);
        printf("\n");
    }
    printf("----\nFINAL    ");
    printl(distance, size);
    printf("\n----\nPATH\n");
    printf(" ");
    for (x = 0; x < size; x++)        
        printf("   %d", x);
    printf("\n");
    for (x = 0; x < size; x++)
    {
        printf(" %d", x);
        printl(path[x], size);
        printf("\n");
    }
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

void fix(int ** vertices, int ** path, int size)
{
    printf("fixin");
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

void printl(int * vertices, int size)
{
    int x;
    for (x = 0; x < size; x++)
    {
        if (vertices[x] == INFINITY)
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
}
