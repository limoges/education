#include <stdio.h>
#include <stdlib.h>

#include <semaphore.h>

int main (int argc, char** argv)
{
  if (argc != 2)
    {
      fprintf(stderr, "usage: %s <sem_name>\n", argv[0]);
      exit(-1);
    }
  int status = sem_unlink(argv[1]);
  if (status < 0)
    {
      perror("sem_unlink");
      exit(-2);
    }
  return 0;
}
