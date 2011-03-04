#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>           /* For O_* constants */
#include <semaphore.h>
#include <sys/mman.h>

#include "utilitaires.c"

int main()
{
	sem_t* sem_affichage;
	sem_affichage = sem_open(SEM_AFFICHAGE, 0);
  	if (sem_affichage == SEM_FAILED)
	{
		perror("joueur:sem_open");
		exit(-1);
	}
	sem_t* sem_mutex;
	sem_mutex = sem_open(SEM_MUTEX, 0);
	if (sem_mutex == SEM_FAILED)
	{
		perror("joueur:sem_open:mutex");
		exit(-3);
	}
	int fd = shm_open(SHM_NAME, O_RDWR, 0777);
	if (fd < 0)
	{
		perror("shm_open");
		exit(-2);
	}
	Tmorpion* pmorpion = (Tmorpion*) mmap(NULL, sizeof(Tmorpion),
				PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
	close(fd); // on n'a plus besoin du descripteur de fichier
	if (pmorpion == (void*)-1)
	{
		perror("mmap");
		exit(-3);
	}
	printf("En attente du mutex...\n");
    	sem_wait(sem_mutex);
	int noJoueur = pmorpion->nbJoueurs++;
	sem_post(sem_mutex);
	int current_nb_joueurs = pmorpion->nbJoueurs;
	char lettre = saisitJoueur(pmorpion->nbJoueurs, pmorpion->carJoue);
	pmorpion->carJoue[noJoueur] = lettre;
	char str_joueur[17], str_suivant[17];
	sprintf(str_joueur, SEM_JOUEUR"%c", 'a'+noJoueur);
	sprintf(str_suivant, SEM_JOUEUR"%c", 'a'+(noJoueur+1)%current_nb_joueurs);
	//printf("%s\n", str_joueur);
	sem_t* sem_joueur; // semaphore du joueur
	sem_joueur = sem_open(str_joueur, O_CREAT|O_EXCL, 0777, 0);
  	if (sem_joueur == SEM_FAILED)
	{
		perror("sem_open:sem_joueur");
		exit(-4);
	}
	sem_t* sem_suivant;
	
	// Lancement de la partie
	if (noJoueur == 1)
	{
		sem_t* sem_init;
		sem_init = sem_open(SEM_JOUEUR"a", 0);
		if (sem_init == SEM_FAILED)
		{
			perror("sem_open:pour post 1er joueur");
		}
		sem_post(sem_init);
	}
	
	
	sem_suivant = sem_open(str_suivant, 0);
	if (sem_suivant == SEM_FAILED)
	{
		perror("suivant rate");
	}
	int x, y;
	while (1)
	{
		printf("Joueur (%d) attend...\n", noJoueur);
		// we wait for the preceding player to signal us through the sem
		sem_wait(sem_joueur);
		// we wait for the mutex to be released
		printf("Joueur (%d) attend pour mutex...\n", noJoueur);
		sem_wait(sem_mutex);
		saisitCoordonnees(&x, &y, pmorpion->morpion);
		pmorpion->morpion[x][y] = lettre;
		// we want to make sure we know who the next player is
		if (pmorpion->nbJoueurs != current_nb_joueurs)
		{
			current_nb_joueurs = pmorpion->nbJoueurs;
			printf("Le nombre de joueur a changé (%d). Recalculation...\n", current_nb_joueurs);
			sprintf(str_suivant, SEM_JOUEUR"%c", 'a'+(noJoueur+1)%current_nb_joueurs); 
			sem_suivant = sem_open(str_suivant, 0);
			if (sem_suivant == SEM_FAILED)
			{
				perror("this should never happen...\n");
			}
		}
		sem_post(sem_mutex);
		// redisplay
		sem_post(sem_affichage);
		// notify the next player
		printf("Signaler le joueur suivant \'%s\'...\n", str_suivant);
		sem_post(sem_suivant);
	}
	exit(0);
}
/*typedef struct {
  int nbJoueurs;                // limite a NB_MAX_JOUEURS
  char carJoue[NB_MAX_JOUEURS]; // carJoue[i] = caractere joue par i
  int score[NB_MAX_JOUEURS];    // score[i] = nb de fois ou 4 caracteres 'carJoue[i]' sont alignes
  char morpion[10][26];         // morpion[i][j] = ' ' si la case (i,j) non jouee
                                //               = caractere d'un joueur sinon
} Tmorpion;*/
