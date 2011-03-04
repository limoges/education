#include <stdio.h>
#include <unistd.h>

char etat_suivant(int i, int j){
  // Hypothèse : tab est un tableau de NB_LIGNESxNB_COLONNES cases
  // retourne le prochain état de la cellule de coordonnées (i,j) dans tab, 
  //en fonction de ses cellules voisines :
  // si (i,j)='-' et si elle a exactement 3 cellules voisines à 'x'
  // alors retourne 'x'
  // sinon si (i,j)='x' et si elle a 2 ou 3 cellules voisines à 'x'
  // alors retourne 'x'
  // sinon retourne '-'
  int k, l;
  int nbVoisins = 0;
  if (i==0) k=0; else k=i-1;
  for (; ((k<NB_LIGNES) && (k<i+2)); k++){
    if (j==0) l=0; else l=j-1;
    for (; ((l<NB_COLONNES) && (l<j+2)); l++) 
      if (tab[k][l]=='x') nbVoisins++;
  }
  if ((tab[i][j]=='x') && ((nbVoisins==3) || (nbVoisins==4))) return 'x';
  if ((tab[i][j]=='-') && (nbVoisins==3)) return 'x';
  return '-';
}

void affiche(){
  // affiche la grille tab[NB_LIGNES][NB_COLONNES]
  static int a = 0;
  int i, j;
  printf("Iteration #%d\n", a++);
  for (i=0; i<NB_LIGNES; i++){
    for (j=0; j<NB_COLONNES; j++)
      printf("%c",tab[i][j]);
    printf("\n");
  }
  fflush(NULL);
}

void init(){
  // initialise la grille tab[NB_LIGNES][NB_COLONNES] 
  // avec NB_LIGNESxNB_COLONNES caractères lus sur l'entrée standard
  int i, j;
  for (i=0; i<NB_LIGNES; i++)
    for (j=0; j<NB_COLONNES; j++)
      read(0,&tab[i][j],1);
}    
