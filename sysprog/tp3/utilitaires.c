#include <stdlib.h>
#include <stdio.h>

#define NB_MAX_JOUEURS 10
#define SHM_NAME "/morpion"
#define SEM_AFFICHAGE "/morpion_affichage"
#define SEM_JOUEUR "/morpion_joueur"
#define SEM_MUTEX "/morpion_mutex"
#define PRINTIT printTableau(pmorpion->carJoue, pmorpion->score);
 
typedef struct {
  int nbJoueurs;                // limite a NB_MAX_JOUEURS
  char carJoue[NB_MAX_JOUEURS]; // carJoue[i] = caractere joue par i
  int score[NB_MAX_JOUEURS];    // score[i] = nb de fois ou 4 caracteres 'carJoue[i]' sont alignes
  char morpion[10][26];         // morpion[i][j] = ' ' si la case (i,j) non jouee
                                //               = caractere d'un joueur sinon
} Tmorpion;


void afficher(Tmorpion* m){
// Postrelation : affiche l'etat courant du morpion puis les scores des joueurs
  int i, j;
  printf("  |a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|\n");
  for (i=0; i<10; i++){
    printf("%c |",'0'+i);
    for (j=0; j<26; j++) printf("%c|",m->morpion[i][j]);
    printf(" %c\n",'0'+i);
  }
  printf("  |a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|\n");
  if (m->nbJoueurs>0){
    printf("\nJoueurs = ");
    for (i=0; i<m->nbJoueurs; i++) printf("%c ",m->carJoue[i]);
    printf("\nScores  = ");
    for (i=0; i<m->nbJoueurs; i++) printf("%d ",m->score[i]);
    printf("\n");
  }
}


char saisitJoueur(int nbJoueurs, char* tab){
// Precondition : tab[0..nbJoueurs-1] contient les lettres minuscules deja utilisees par les joueurs
// Postrelation : retourne une lettre minuscule differente de celles contenues dans tab[0..nbJoueurs-1]
  int i = 0;
  char c;
  system("stty -icanon"); // Pour que les caracteres saisis ne soient pas buffeurises...
  while (1){
    printf("Entrez votre lettre : ");
    c = getchar();
    printf("\n");
    if (c != 0 && ((c<'a') || (c>'z'))) {
	  printf("La lettre doit etre une minuscule comprise entre 'a' et 'z'\n");
    }
    else {
     for (i=0; ((i<nbJoueurs) && (c!=tab[i])); i++);
      if (i==nbJoueurs) return c; 
	printf("La lettre %c est deja utilisee par un autre joueur ; recommencez\n",c);
    }
  }
}

void printTableau(char* chars, int* scores)
{
	int i;
	for (i = 0; i < NB_MAX_JOUEURS; i++)
	{
		printf("carJoue[%d] = \'%c\' = %d\n", i, chars[i], scores[i]);
	}
	return;
}

void saisitCoordonnees(int *x, int *y, char morpion[10][26]){
// postrelation : *x = numero de ligne (compris entre '0' et '9')
//                *y = numero de colonne (compris entre '0' et '25')
//                tels que morpion[x][y] n'a pas deja ete joue
  while(1){
    printf("C'est a vous de jouer : ligne=");
    *x = getchar() - '0';
    if ((*x<0) || (*x>9))
      printf("\nLe numero de ligne doit etre compris entre 0 et 9. Recommencez.\n");
    else {
      printf(" colonne=");
      *y = getchar() - 'a'; printf("\n");
      if ((*y<0) || (*y>25))
	printf("\nLe numero de ligne doit etre compris entre a et z. Recommencez.\n");
      else if (morpion[*x][*y] != ' ')
	printf("\nLa case (%c,%c) est deja utilisee par %c\n",'a'+*x,'a'+*y,morpion[*x][*y]);
      else return;
    }
  }
}


int test(int x, int y, char morpion[10][26]){
  // Precondition : morpion[x][y] contient le caractere qui vient d'etre joue
  // Postrelation : Si morpion[x][y] appartient a une suite de 4 caracteres consecutifs identiques
  //                Alors met ces 4 caracteres en majuscule et retourne 1
  //                Sinon retourne 0
  int i, j, cpt;
  // test sur la ligne x
  cpt = 1;
  for (i=x-1; ((i>=0) && (morpion[i][y]==morpion[x][y])); i--) cpt++;
  for (i=x+1; ((i<26) && (morpion[i][y]==morpion[x][y])); i++) cpt++;
  if (cpt == 4){
    for (i=x-1; ((i>=0) && (morpion[i][y]==morpion[x][y])); i--) morpion[i][y] += 'A'-'a';
    for (i=x+1; ((i<26) && (morpion[i][y]==morpion[x][y])); i++) morpion[i][y] += 'A'-'a';
    morpion[x][y] += 'A' - 'a';
    return 1;
  }
  // test sur la colonne y
  cpt = 1;
  for (i=y-1; ((i>=0) && (morpion[x][i]==morpion[x][y])); i--) cpt++;
  for (i=y+1; ((i<26) && (morpion[x][i]==morpion[x][y])); i++) cpt++;
  if (cpt == 4){
    for (i=y-1; ((i>=0) && (morpion[x][i]==morpion[x][y])); i--) morpion[x][i] += 'A'-'a';
    for (i=y+1; ((i<26) && (morpion[x][i]==morpion[x][y])); i++) morpion[x][i] += 'A'-'a';
    morpion[x][y] += 'A' - 'a';
    return 1;
  }
  // test sur la diagonale 1
  cpt = 1;
  i=y-1; j=x-1; 
  while ((i>=0) && (j>=0) && (morpion[j][i]==morpion[x][y])){ cpt++; i--; j--; }
  i=y+1; j=x+1;
  while ((i<26) && (j<26) && (morpion[j][i]==morpion[x][y])){ cpt++; i++; j++; }
  if (cpt == 4){
    i=y-1; j=x-1; 
    while ((i>=0) && (j>=0) && (morpion[j][i]==morpion[x][y])){
      morpion[j][i] += 'A'-'a'; i--; j--;
    }
    i=y+1; j=x+1;
    while ((i<26) && (j<26) && (morpion[j][i]==morpion[x][y])){
      morpion[j][i] += 'A'-'a'; i++; j++;
    }
    morpion[x][y] += 'A' - 'a';
    return 1;
  }
  // test sur la diagonale 2
  cpt = 1;
  i=y-1; j=x+1; 
  while ((i>=0) && (j<26) && (morpion[j][i]==morpion[x][y])){ cpt++; i--; j++; }
  i=y+1; j=x-1;
  while ((i<26) && (j>=0) && (morpion[j][i]==morpion[x][y])){ cpt++; i++; j--; }
  if (cpt == 4){
    i=y-1; j=x+1; 
    while ((i>=0) && (j<26) && (morpion[j][i]==morpion[x][y])){
      morpion[j][i] += 'A'-'a'; i--; j++;
    }
    i=y+1; j=x-1;
    while ((i<26) && (j>=0) && (morpion[j][i]==morpion[x][y])){
      morpion[j][i] += 'A'-'a'; i++; j--;
    }
    morpion[x][y] += 'A' - 'a';
    return 1;
  }
  return 0;
}
