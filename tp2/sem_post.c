#include <stdio.h>
#include <stdlib.h>

#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */
#include <semaphore.h>

int main (int argc, char** argv)
{
  if (argc != 2)
    {
      fprintf(stderr, "usage: %s <sem_name>\n", argv[0]);
      exit(-1);
    }
  sem_t *sem = sem_open(argv[1], 0);
  if (sem == SEM_FAILED)
    {
      perror("sem_open");
      exit(-2);
    }
  int status = sem_post(sem);
  if (status < 0)
    {
      perror("sem_post");
      exit(-3);
    }
  sem_close(sem);
  return 0;
}
