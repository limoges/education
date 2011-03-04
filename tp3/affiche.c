#include <fcntl.h>           /* For O_* constants */
#include <semaphore.h>
#include <sys/mman.h> /*for mmap/munmap*/
#include <stdio.h> /*for perror*/
#include <unistd.h> /*for close*/

#include "utilitaires.c"

int main()
{
	sem_t* sem_affichage;
	sem_affichage = sem_open(SEM_AFFICHAGE, 0);
  	if (sem_affichage == SEM_FAILED)
	{
		perror("affichage:sem_open");
		exit(-1);
	}
	sem_t* sem_mutex;
	sem_mutex = sem_open(SEM_MUTEX, 0);
	if (sem_mutex == SEM_FAILED)
	{
		perror("affichage:sem_open:MUTEX");
		exit(-4);
	}
	int fd = shm_open(SHM_NAME, O_RDWR, 0777);
	if (fd < 0)
	{
		perror("shm_open");
		exit(-2);
	}
	Tmorpion* pmorpion = (Tmorpion*) mmap(NULL, sizeof(Tmorpion), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	close(fd); // on n'a plus besoin du descripteur de fichier
	if (pmorpion == (void*)-1)
	{
		perror("mmap");
		exit(-3);
	}
	while (1)
	{
		printf("Attend mutex...\n");
		sem_wait(sem_mutex);
		afficher(pmorpion);
		printf("Post mutex...\n");
		sem_post(sem_mutex);
		printf("Attend affichage...\n");
		sem_wait(sem_affichage);
	}
}
