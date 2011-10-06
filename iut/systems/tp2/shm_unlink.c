#include <stdio.h>
#include <stdlib.h>

#include <sys/mman.h>

int main (int argc, char** argv)
{
  if (argc != 2)
    {
      fprintf(stderr, "usage: %s <shm_name>\n", argv[0]);
      exit(-1);
    }
  int status = shm_unlink(argv[1]);
  if (status < 0)
    {
      perror("shm_unlink");
      exit(-2);
    }
  return 0;
}
