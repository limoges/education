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
  sem_t *sem = sem_open(argv[1], O_CREAT|O_EXCL, 0777, 0);
  if (sem == SEM_FAILED)
    {
      perror("sem_open");
      exit(-2);
    }
  sem_close(sem);
  return 0;
}
