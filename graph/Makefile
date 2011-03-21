TARGET = djikstra kruskal prim bellman-ford

all: djikstra kruskal prim bellman-ford

djikstra: djikstra.c utils.c
	$(CC) $(CPPFLAGS) -o djikstra djikstra.c

kruskal: kruskal.c utils.c
	$(CC) $(CPPFLAGS) -o kruskal kruskal.c

prim: prim.c utils.c
	$(CC) $(CPPFLAGS) -o prim prim.c

bellman-ford: bellman-ford.c utils.c
	$(CC) $(CPPFLAGS) -o bellman-ford bellman-ford.c

clean:
	rm $(TARGET)
