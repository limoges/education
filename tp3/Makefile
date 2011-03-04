CC = gcc -Wall -Werror

UNAME := $(shell uname)
ifeq ($(UNAME), Linux)
CC += -lrt
endif
ifeq ($(UNAME), Darwin)
# no special parameter required for the moment
endif

NAME = tp3-limoges-nguyen
TARGETS = initialise detruit affiche joueur

all: ${TARGETS}

super: ${TARGETS}
	./detruit
	./initialise

clean:
	rm ${TARGETS}
archive:
	tar czf ../${NAME}.tar.gz *.c *.h Makefile

%: %.c utilitaires.c
	$(CC) "$<" -o "$@"


