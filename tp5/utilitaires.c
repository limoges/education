#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

typedef struct {
  char nom;
  int qte;
} Tpaysan;

int nouveau_paysan(Tpaysan **p){
  // lit un charactere et un entier sur l'entree standard 
  // retourne 0 si la fin de fichier est detectee
  // sinon :
  // - cree une nouvelle structure de type Tpaysan avec ces valeurs lues
  // - met dans *p l'adresse de cette nouvelle structure
  // - retourne 1
  int nb_lus;
  char c, nom;
  int qte;
  nb_lus = read(0,&c,1); 
  while ((nb_lus==1) && ((c<'a') || (c>'z'))) nb_lus = read(0,&c,1);
  if (nb_lus == 0) return 0;
  nom = c;
  nb_lus = read(0,&c,1); 
  while ((nb_lus==1) && ((c<'0') || (c>'9'))) nb_lus = read(0,&c,1);
  if (nb_lus == 0) return 0;
  qte = 0;
  while ((nb_lus==1) && (c>='0') && (c<='9')){
    qte = qte*10 + c-'0';
    nb_lus = read(0,&c,1);
  }
  *p = (Tpaysan*)malloc(sizeof(Tpaysan)); 
  (**p).qte = qte;
  (**p).nom = nom;
  return 1;
}

void affiche(int nb_attente, Tpaysan** tab){
  // affiche l'état de la place du village
  int i;

  for (i=0; ((i<nb_attente) && (i<NB_ROBINETS)); i++) printf("%c/%x ",tab[i]->nom, tab[i]->qte);
  for (; i<NB_ROBINETS; i++) printf(" -  ");
  printf("| ");
  for (; ((i<nb_attente) && (i<NB_ROBINETS+NB_OMBRE)); i++) printf("%c/%x ",tab[i]->nom, tab[i]->qte);
  for (; i<NB_ROBINETS+NB_OMBRE; i++) printf(" -  ");
  printf("| ");
  for (;i<nb_attente; i++) printf("%c/%x ",tab[i]->nom, tab[i]->qte);
  printf("\n");
  fflush(stdout);
}
