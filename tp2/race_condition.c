#include <fcntl.h>           /* For O_* constants */
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>        /* For mode constants */
#include <sys/wait.h>
#include <unistd.h>

#define NAME "/race_condition"
#define SIZE sizeof(int)
#define NBP 4
#define NBI 100000

void fils(int* ptr)
{
  int i;
  for (i=0; i<NBI; i++)
    {
      *ptr += 1;
    }
  fprintf(stderr, "fils %d terminé\n", getpid());
  exit(-1);
}

void menage()
{
  shm_unlink(NAME);
}

int main (int argc, char** argv)
{
  atexit(menage); // assure que les ressources partagées seront bien détruites

  int fd = shm_open(NAME, O_CREAT|O_EXCL|O_RDWR, 0777);
  if (fd < 0) { perror("shm_open"); exit(-1); }
  int status = ftruncate(fd, SIZE);
  if (status < 0) { perror("truncate"); exit(-2); }
  int *ptr = (int*) mmap(NULL, SIZE, PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
  if (ptr == (void*)-1) { perror("mmap"); exit(-3); }
  close(fd); // on n'a plus besoin du descripteur de fichier

  int i, pid;
  for (i=0; i<NBP; i++)
    {
      pid = fork();
      if (pid < 0) { perror("fork"); exit(-4); }
      else if (pid == 0) { fils(ptr); }
    }

  for (i=0; i<NBP; i++) { pid = wait(&status); }
  printf("valeur finale: %d / %d\n", *ptr, NBP*NBI);

  munmap(ptr, SIZE);
  return 0;
}
