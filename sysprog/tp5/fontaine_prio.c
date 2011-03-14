#include <time.h>
#include <stdlib.h>
#include <pthread.h>

#include <limits.h>

#define NB_ROBINETS 2
#define NB_OMBRE 4
#define TOTAL 1000

#include "utilitaires.c"

time_t t_debut;
pthread_mutex_t mutex; // ? WHAT FOR
pthread_cond_t cond_ombre, cond_fontaine;
pthread_t threads[TOTAL];
Tpaysan * paysans[TOTAL];
int nb_attente = 0;

#define ARRIVE 1
#define QUITTE 0
void afficher(int cas, time_t temps, Tpaysan p)
{
    if (temps < 10)
        printf(" %d", (int) temps);
    else
        printf("%d", (int) temps);
    if (cas == ARRIVE)
    {
        if (p.qte < 10)
            printf(": %c arrive avec  %d seaux     ", p.nom, p.qte);
        else
            printf(": %c arrive avec %d seaux     ", p.nom, p.qte);
    }
    else
    {
        printf(": %c est servi... il s'en va  ", p.nom);
    }
    affiche(nb_attente, paysans);
}

// You should already have the mutex.
void* attendre(void* data)
{
    Tpaysan * p = (Tpaysan *) data;
    afficher(ARRIVE, time(NULL) - t_debut, *p);
    pthread_mutex_unlock(&mutex);
   
    int rang, i, prochain, min;
    while (1)
    {
        pthread_mutex_lock(&mutex);
        for (rang = 0; rang < TOTAL && p->nom != paysans[rang]->nom; rang++);
        if (rang < NB_ROBINETS)
        {
            pthread_mutex_unlock(&mutex);
            sleep(p->qte); 
            pthread_mutex_lock(&mutex);
            for (rang = 0; rang < TOTAL && p->nom != paysans[rang]->nom; rang++);
            for (i = NB_ROBINETS, min = INT_MAX; i < (NB_OMBRE + NB_ROBINETS) && i < nb_attente; i++)
            {
                if (paysans[i]->qte < min)
                {
                    min = paysans[i]->qte;
                    prochain = i;
                }
            }
            paysans[rang] = paysans[prochain];
            for (i = prochain; i < nb_attente; i++)
                paysans[i] = paysans[i+1];
            nb_attente--;
            afficher(QUITTE, time(NULL) - t_debut, *p);
            free(p);
            pthread_cond_broadcast(&cond_fontaine);
            pthread_cond_broadcast(&cond_ombre);
            pthread_mutex_unlock(&mutex);
            return NULL;
        }
        else if (rang < (NB_ROBINETS + NB_OMBRE))
        {
            pthread_cond_wait(&cond_fontaine, &mutex);
            pthread_mutex_unlock(&mutex);
        }
        else
        {
            pthread_cond_wait(&cond_ombre, &mutex);
            pthread_mutex_unlock(&mutex);
        }
    }
    return NULL;
}

int main()
{
    t_debut = time(NULL);
    if (pthread_mutex_init(&mutex, NULL) != 0)
    {
        perror("pthread_mutex_init(&mutex...");
        exit(EXIT_FAILURE);
    }
    if (pthread_cond_init(&cond_ombre, NULL) != 0)
    {
        perror("pthread_cond_init(&cond_ombre...");
        exit(EXIT_FAILURE);
    }
    if (pthread_cond_init(&cond_fontaine, NULL) != 0)
    {
        perror("pthread_cond_init(&cond_fontaine...");
        exit(EXIT_FAILURE);
    }
    int i = 0;
    Tpaysan ** paysan;
   // attend sur l'entrÃe standard qu'un paysan "arrive".
   while (nouveau_paysan(paysan))
   {
       pthread_mutex_lock(&mutex);
       paysans[nb_attente++] = *paysan;
       //lorsqu'un un paysan arrive, on crÃÃ©son thread
       if (pthread_create(threads+i, NULL, attendre, *paysan) != 0)
       {
           perror("pthread_create");
            //exit(EXIT_FAILURE);
       }
       i++;
    }
    int j;
    for (j = 0; j < i; j++)
    {
        if (pthread_join(threads[j], NULL) != 0)
        {
            perror("pthread_join");
            exit(EXIT_FAILURE);
        }
    }
    exit(EXIT_SUCCESS);
}
