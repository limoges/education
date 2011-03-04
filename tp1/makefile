// Compiles on OSX 10.6.6
CC = gcc
CCFLAGS  = #-Wall -Werror
COMPILE  = $(CC) $(CCFLAGS) -c
LINK = $(CC) $(CCFLAGS) -o

help :
	@echo "You can either :"
	@echo "make all / clean / crypted / puzzle"
all : crypted puzzle
	@echo "building all..."
crypted : crypted.o
	$(LINK) crypted.executable crypted.o
crypted.o : crypted.c
	$(COMPILE) crypted.c
puzzle : puzzle.o
	$(LINK) puzzle.executable puzzle.o
puzzle.o : puzzle.c
	$(COMPILE) puzzle.c	
clean :
	rm *.o *.executable
.PHONY : all puzzle crypted clean
