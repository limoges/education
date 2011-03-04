#include <fcntl.h>           /* For O_* constants */
#include <stdio.h> /*for perror*/
#include <unistd.h> /*for close*/
#include <semaphore.h>
#include <sys/mman.h>

#include "utilitaires.c" // for struct

int main()
{
	int fd = shm_open(SHM_NAME, O_RDWR, 0777);
	if (fd < 0)
	{
		perror("shm_open");
	}
	if (shm_unlink(SHM_NAME) < 0)
	{
		perror("shm_unlink");
	}
	if (sem_unlink(SEM_AFFICHAGE) < 0)
	{
		perror("sem_unlink");
	}
	if (sem_unlink(SEM_MUTEX) < 0)
	{
		perror("sem_unlink");
	}
	Tmorpion* pmorpion = (Tmorpion*) mmap(NULL, sizeof(Tmorpion), PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	close(fd); // on n'a plus besoin du descripteur de fichier
	if (pmorpion == (void*)-1)
	{
		perror("mmap");
	}
	int i;
	char str_sem[17];
	for (i=0; i < NB_MAX_JOUEURS; i++)
	{
		sprintf(str_sem, SEM_JOUEUR"%c", 'a'+i);
		if (sem_unlink(str_sem) < 0)
			perror(str_sem);
	}
	if (munmap((void*)pmorpion, sizeof(Tmorpion)) < 0)
	{
		perror("munmap");
	}
	exit(0);
}
