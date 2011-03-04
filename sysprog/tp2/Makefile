SEMAPHORES=sem_create sem_getvalue sem_post sem_wait sem_unlink
SHARED_MEM=shm_create shm_write shm_read shm_unlink shm_upper10

all: ${SEMAPHORES} ${SHARED_MEM} race_condition

clean:
	rm -f  ${SEMAPHORES} ${SHARED_MEM} race_condition *~

archive:
	tar czf ../tp2.tar.gz SUJET.txt Makefile *.c *.sh

%: %.c
	gcc -Wall -Werror "$<" -o "$@"

## pour FreeBSD

.SUFFIXES: .c

.c:
	gcc -Wall -Werror ${.IMPSRC} -o ${.TARGET}
