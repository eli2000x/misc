# Makefile for queueClient.c

FLAGS    = -std=c99 -Wall
SOURCES  = queueClient.c queue.c
OBJECTS  = queueClient.o queue.o
EXEBIN   = queueClient

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean : 
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full queueClient queueClient.c out.txt