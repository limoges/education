#include <fcntl.h>           /* For O_* constants */
#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>        /* For mode constants */
#include <sys/wait.h>
#include <unistd.h>
#include <semaphore.h>

#include "utilitaires.c" //for struct

int main()
{
	//initialisation de la memoire partagee	
	int fd = shm_open(SHM_NAME, O_CREAT|O_RDWR, 0777);
	if (fd < 0)
	{
		perror("shm_open");
		exit(-1);
	}
	int errno = ftruncate(fd, sizeof(Tmorpion));
	if (errno < 0)
	{
		printf("err no %d", errno);
		perror("ftruncate");
		exit(-2);
	}
	Tmorpion* pmorpion = (Tmorpion*) mmap(NULL, sizeof(Tmorpion), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	close(fd); // on n'a plus besoin du descripteur de fichier
	if (pmorpion == (void*)-1)
	{
		perror("mmap");
		exit(-3);
	}
	pmorpion->nbJoueurs = 0;
	int i,j;
	for (i=0; i<10; i++)
		for (j=0; j<26; j++)
			pmorpion->morpion[i][j] = ' ';
	sem_t* sem_affichage;
	sem_affichage = sem_open(SEM_AFFICHAGE, O_CREAT|O_EXCL|O_RDWR, 0777, 0);
  	if (sem_affichage == SEM_FAILED)
	{
		perror("sem_open");
		exit(-4);
	}
	sem_t* sem_mutex;
	sem_mutex = sem_open(SEM_MUTEX, O_CREAT|O_EXCL|O_RDWR, 0777, 1);
	if (sem_mutex == SEM_FAILED)
	{
		perror("sem_open");
		exit(-5);
	}
	exit(0);
}
