#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NB_THREAD 4
#define NB_ITER 1000000

typedef struct {
  int* buffer;
  int nb_iter;
} test1_data;
pthread_mutex_t mutex;

void* test1(void* data)
{
  test1_data* d = (test1_data*) data;
  int i;
  pthread_mutex_lock(&mutex); 
  for (i=0; i < d->nb_iter; i++)
    {
      // this is what we need to protect with the mutex.
      (*d->buffer) += 1;
      // end mutex protection
    }
  pthread_mutex_unlock(&mutex);
  return NULL;
}


int main()
{
  int status, i, buffer;
  pthread_t t[NB_THREAD];
  test1_data d[NB_THREAD];

  if (pthread_mutex_init(&mutex, NULL) < 0)
  {
    perror("pthread_mutex_init");
    exit(-10);
  }
  buffer = 0;
  for (i=0; i<NB_THREAD; i++)
    {
      d[i].buffer = &buffer;
      d[i].nb_iter = NB_ITER;
      status = pthread_create(t+i, NULL, test1, &d[i]);
      /**/ if (status != 0) { perror("pthread_create"); exit(-1); }
    }
  // Le père attend que les fils se terminent pour finir
  for (i=0; i<NB_THREAD; i++)
    {
      status = pthread_join(t[i], NULL);
      /**/ if (status != 0) { perror("pthread_join"); exit(-2); }
    }
  printf("%d/%d\n", buffer, NB_THREAD*NB_ITER);
  return 0;
}
