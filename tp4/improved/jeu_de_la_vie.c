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
pthread_t threads[1+(NB_LIGNES*NB_COLONNES)];

#include "utilitaires.c"

void next()
{
  pthread_mutex_lock(&mutex);
  count++;
  if (count < ((NB_COLONNES*NB_LIGNES)+1)) // +1 for afficheur
  {
    pthread_cond_wait(&cond, &mutex);
    pthread_mutex_unlock(&mutex);
    return;
  }
  else
  {
    count = 0;
    pthread_mutex_unlock(&mutex);
    pthread_cond_broadcast(&cond);
    return;
  }
}
void* afficheur()
{
  while (1)
  {
    affiche();
    next();
    next();
    sleep(1);
  } 
  return NULL;
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
    next();
  }
  return NULL;
}

int main()
{
  init();
  int i = 0;
  count = 0;
  if (pthread_mutex_init(&mutex, NULL) != 0)
  {
    perror("pthread_mutex_init");
    exit(-1);
  }
  if (pthread_cond_init(&cond, NULL) != 0)
  {
    perror("pthread_cond_init");
    exit(-2);
  }
  for (i = 0; i < (NB_LIGNES*NB_COLONNES); i++)
  {
    data[i].x = i / NB_COLONNES;
    data[i].y = i % NB_COLONNES;
    if (pthread_create(threads+i, NULL, cell, data+i) != 0)
    {
      perror("pthread_create");
      exit(-3);
    }
  } 
  if (pthread_create(threads+i, NULL, afficheur, NULL) != 0)
  {
    perror("pthread_create::afficheur");
    exit(-5);
  }
  // wait for all threads to be terminated to terminate the main process
  for (i = 0; i < 1+(NB_LIGNES*NB_COLONNES); i++)
  {
    if (pthread_join(threads[i], NULL) != 0)
    {
      perror("pthread_join");
      exit(-4);
    }
  }
  return 0;
}
