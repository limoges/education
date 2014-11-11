CFLAGS+=
# Enable most warning and make them errors
CFLAGS+=-Wall
CFLAGS+=-Werror 
# Function size are platform dependent
CFLAGS+=-Wpointer-arith

LDFLAGS+=-pthread

default: midterm

midterm: midterm.c provided.h
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@


.SUFFIXES: .c .o

clean:
	$(RM) midterm
