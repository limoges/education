#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NB_COLONNES 20
#define NB_LIGNES 10

typedef struct
{
  int x, y;
} coord;

char tab[NB_LIGNES][NB_COLONNES];
coord data[NB_COLONNES*NB_LIGNES];
pthread_mutex_t mutex;
pthread_cond_t cond;
int count;
pthread_t threads[NB_LIGNES*NB_COLONNES];

#include "utilitaires.c"

int next()
{
  pthread_mutex_lock(&mutex);
  count++;
  if (count < (NB_COLONNES*NB_LIGNES))
  {
    pthread_cond_wait(&cond, &mutex);
    pthread_mutex_unlock(&mutex);
    return 0;
  }
  else
  {
    count = 0;
    pthread_mutex_unlock(&mutex);
    pthread_cond_broadcast(&cond);
    return 1;
  }
}

void* cell(void* data)
{
  coord * xy = (coord*) data;
  char c;
  while (1)
  {
    c = etat_suivant(xy->x, xy->y);
    next();
    tab[xy->x][xy->y] = c;
    if (next())
    {
      sleep(1);
      affiche();
    }
  }
  return NULL;
}

int main()
{
  init();
  int i;
  count = 0;
  // initialize counter mutex;
  if (pthread_mutex_init(&mutex, NULL) != 0)
  {
    perror("pthread_mutex_init");
    exit(-1);
  }
  // initialize counter condition;
  if (pthread_cond_init(&cond, NULL) != 0)
  {
    perror("pthread_cond_init");
    exit(-2);
  }
  affiche();
  // create a thread for each cell in the 2d array
  for (i = 0; i < NB_LIGNES*NB_COLONNES; i++)
  {
    data[i].x = i / NB_COLONNES;
    data[i].y = i % NB_COLONNES;
    // creation of a thread without parameters and with the thread's coordinates
    if (pthread_create(threads+i, NULL, cell, data+i) != 0)
    {
      perror("pthread_create");
      exit(-3);
    }
    //printf("Thread created outer[%d,%d]\n", data[i].x, data[i].y);
  }
  // wait for all threads to be terminated to terminate the main process
  for (i = 0; i < NB_LIGNES*NB_COLONNES; i++)
  {
    if (pthread_join(threads[i], NULL) != 0)
    {
      perror("pthread_join");
      exit(-4);
    }
  }
  return 0;
}
