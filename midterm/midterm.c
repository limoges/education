#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include "provided.h"

pthread_t command_thread;
pthread_t data_thread[3];
pthread_t status_thread;
pthread_t server_thread;

pthread_attr_t command_attr;
pthread_attr_t data_attr[3];
pthread_attr_t status_attr;
pthread_attr_t server_attr;

pthread_mutex_t command_mutex;
pthread_mutex_t data_mutex;
pthread_mutex_t status_mutex;
pthread_mutex_t server_mutex;

sem_t command_sem;
sem_t data_sem;
sem_t status_sem;
sem_t server_sem;

void* command_task(void* args);
void* data_task(void* arg);
void* status_task(void* arg);
void* server_task(void* arg);

void create_thread(
        pthread_t *id,
        pthread_attr_t *attr,
        void *(*func)(void *), 
        int *running, 
        int priority) {
    struct sched_param params;
    pthread_attr_init(attr);
    pthread_attr_getschedparam(attr, &params);
    params.sched_priority = priority;
    pthread_attr_setschedparam(attr, &params);
    pthread_create(id, attr, func, running);
}

int running;
void interrupt_handler(int code) {
        running = 0;
        puts("\nreceived kill signal");
}


int main(void)
{
        running = 1;
        signal(SIGINT, interrupt_handler);
        sem_init(&command_sem, 0, 0);
        sem_init(&data_sem, 0, 0);
        sem_init(&status_sem, 0, 0);
        sem_init(&server_sem, 0, 0);
        pthread_mutex_init(&command_mutex, NULL);
        pthread_mutex_init(&data_mutex, NULL);
        pthread_mutex_init(&status_mutex, NULL);
        pthread_mutex_init(&server_mutex, NULL);
        puts("starting threads...");
        create_thread(&server_thread,   &server_attr,   server_task,    &running,   60);
        create_thread(&command_thread,  &command_attr,  command_task,   &running,   50);
        create_thread(&data_thread[0],  &data_attr[0],  data_task,      &running,   40);
        create_thread(&data_thread[1],  &data_attr[1],  data_task,      &running,   40);
        create_thread(&data_thread[2],  &data_attr[2],  data_task,      &running,   40);
        create_thread(&status_thread,   &status_attr,   status_task,    &running,   30);
        
        pthread_join(server_thread, NULL);
        pthread_join(command_thread, NULL);
        pthread_join(data_thread[0], NULL);
        pthread_join(data_thread[1], NULL);
        pthread_join(data_thread[2], NULL);
        pthread_join(status_thread, NULL);
        sem_destroy(&command_sem);
        sem_destroy(&data_sem);
        sem_destroy(&status_sem);
        sem_destroy(&server_sem);
        pthread_mutex_destroy(&command_mutex);
        pthread_mutex_destroy(&data_mutex);
        pthread_mutex_destroy(&status_mutex);
        pthread_mutex_destroy(&server_mutex);
        return EXIT_SUCCESS;
}

void* data_task(void* arg)
{
        int *isRunning;
        int local;
        
        isRunning = arg;
        printf("%s started.\n", __func__);
        while (*isRunning) {
                read_data(&local);
                pthread_mutex_lock(&data_mutex);
                put_data(local);
                pthread_mutex_unlock(&data_mutex);
                sem_post(&data_sem);
                sem_post(&server_sem);
        }
        printf("%s completed.\n", __func__);
        return NULL;
}

void* command_task(void* arg)
{
        int* isRunning;
        int local;
        
        isRunning = arg;
        printf("%s started.\n", __func__);
        while (*isRunning) {
                read_command(&local);
                pthread_mutex_lock(&command_mutex);
                put_command(local);
                pthread_mutex_unlock(&command_mutex);
                sem_post(&command_sem);
                sem_post(&server_sem);
        }
        printf("%s completed.\n", __func__);
        return NULL;
}

void* status_task(void* arg)
{
        int* isRunning;
        int local;
        
        isRunning = arg;
        printf("%s started.\n", __func__);
        while (*isRunning) {
                read_status(&local);
                pthread_mutex_lock(&status_mutex);
                put_status(local);
                pthread_mutex_unlock(&status_mutex);
                sem_post(&status_sem);
                sem_post(&server_sem);
        }
        printf("%s completed.\n", __func__);
        return NULL;
}

void* server_task(void* arg)
{
        int* isRunning;
        int local = 0;
        
        isRunning = arg;
        printf("%s started.\n", __func__);
        while (*isRunning) {
                sem_wait(&server_sem);
                if (sem_trywait(&command_sem) == 0) {
                        pthread_mutex_lock(&command_mutex);
                        remove_command(&local);
                        pthread_mutex_unlock(&command_mutex);
                }
                else if (sem_trywait(&data_sem) == 0) {
                        pthread_mutex_lock(&data_mutex);
                        remove_data(&local);
                        pthread_mutex_unlock(&data_mutex);
                }
                else if (sem_trywait(&status_sem) == 0) {
                        pthread_mutex_lock(&status_mutex);
                        remove_status(&local);
                        pthread_mutex_unlock(&status_mutex);
                }
                //putchar(local);
                putchar(local);
        }
        printf("%s completed.\n", __func__);
        return NULL;
}





