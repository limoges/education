#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/mman.h>
#include <sys/stat.h>        /* For mode constants */
#include <fcntl.h>           /* For O_* constants */

#define SIZE 100

int main (int argc, char** argv)
{
  if (argc != 2)
    {
      fprintf(stderr, "usage: %s <shm_name>\n", argv[0]);
      exit(-1);
    }
  int fd = shm_open(argv[1], O_CREAT|O_EXCL|O_RDWR, 0777);
  if (fd < 0)
    {
      perror("shm_open");
      exit(-2);
    }
  int status = ftruncate(fd, SIZE); // fixe la taille de la zone mémoire
  if (status < 0)
    {
      perror("ftruncate");
      exit(-2);
    }
  close(fd); // NB: le même 'close' que pour les fichiers classiques
  return 0;
}
