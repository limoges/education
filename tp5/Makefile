NAME=tp5
ARCHIVE=Makefile fontaine.c journee_type utilitaires.c fontaine.txt fontaine_prio.txt
TARGETS=fontaine fontaine_prio

all: ${TARGETS}

test: fontaine
	./journee_type | ./fontaine
test_fontaine: fontaine
	./journee_type | ./fontaine | tee fontaine.test
	diff fontaine.txt fontaine.test

test_fontaine_prio: fontaine_prio
	./journee_type | ./fontaine_prio | tee fontaine_prio.test
	diff fontaine_prio.txt fontaine_prio.test

clean:
	rm -f ${TARGETS} *~ *.test

archive:
	tar czf ../${NAME}.tar.gz ${ARCHIVE}

%: %.c utilitaires.c
	gcc -Wall -Werror -pthread "$<" -o "$@"
