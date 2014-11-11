#ifndef PROVIDED_H
#define PROVIDED_H

#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <unistd.h>

static FILE * file_command;
static FILE * file_data;
static FILE * file_status;

int rand_limit(int limit) {
        //int divisor = RAND_MAX / (limit + 1);
        //int ret;
        //do {
        //        ret = rand() / divisor;
        //} while (ret > limit);
        //return ret;
        return rand () % limit + 1;
}

void constructor_provided(void) __attribute__ ((constructor));
void destructor_provided(void) __attribute__ ((destructor));

void constructor_provided(void) {
        setvbuf(stdout, NULL, _IONBF, 0);
        puts("initalizing provided structures...");
        file_command = tmpfile();
        if (file_command == NULL) {
                puts("failed to open file_command");
                exit(EXIT_FAILURE);
        }
        file_data = tmpfile();
        if (file_data == NULL) {
                puts("failed to open file_command");
                exit(EXIT_FAILURE);
        }
        file_status = tmpfile();
        if (file_status == NULL) {
                puts("failed to open file_command");
                exit(EXIT_FAILURE);
        }

        srand(time(NULL));
}

void destructor_provided(void) {
        fclose(file_command);
        file_command = NULL;
        fclose(file_data);
        file_data = NULL;
        fclose(file_status);
        file_status = NULL;
}

#define WAIT 10000
void read_command(int *value) {
        usleep(WAIT * rand_limit(10));
        *value = '0';
}

void read_data(int *value) {
        usleep(WAIT * rand_limit(10));
        *value = 'A';
}

void read_status(int *value) {
        usleep(WAIT * rand_limit(10));
        *value = 'a';
}

void put_command(int value) {
        fseek(file_command, 0, SEEK_END);
        int ret = fputc(value, file_command);
        fflush(file_command);
        if (ret != value) {
                printf("%s failed\n", __func__);
        }
}

void put_data(int value) {
        fseek(file_data, 0, SEEK_END);
        int ret = fputc(value, file_data);
        fflush(file_data);
        if (ret != value) {
                printf("%s failed\n", __func__);
        }
}

void put_status(int value) {
        fseek(file_status, 0, SEEK_END);
        int ret = fputc(value, file_status);
        fflush(file_status);
        if (ret != value) {
                printf("%s failed\n", __func__);
        }
}

void remove_command(int *value) {
        fseek(file_command, 0, SEEK_SET);
        *value = fgetc(file_command);
}

void remove_data(int *value) {
        fseek(file_data, 0, SEEK_SET);
        *value = fgetc(file_data);
}

void remove_status(int *value) {
        fseek(file_status, 0, SEEK_SET);
        *value = fgetc(file_status);
}

#define handle(x) putchar(x);
//void handle(int value) {
//        putc(value, stdout);
//}

#endif /* PROVIDED_H */

