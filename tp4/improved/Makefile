NAME=tp4
ARCHIVE= Makefile race_condition_fixed.c jeu_de_la_vie.c race_condition.c utilitaires.c tableau1 tableau2
TARGETS=race_condition race_condition_fixed jeu_de_la_vie

all: ${TARGETS}

clean:
	rm -f ${TARGETS} *~

archive:
	tar czf ../${NAME}.tar.gz ${ARCHIVE}

%: %.c utilitaires.c
	gcc -Wall -Werror -pthread "$<" -o "$@"

## pour FreeBSD

.SUFFIXES: .c

.c: utilitaires.c
	gcc -Wall -Werror -pthread ${.IMPSRC} -o ${.TARGET}
