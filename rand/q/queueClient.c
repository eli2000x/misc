// Name: Erik Li
// CruzID: ersli
// Role: Lab5 - queues in C
// File Name: queueClient.c

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#include "queue.h"

int main(int argc, char* argv[]) {

	// files for input and output
	FILE* in;
	FILE* out;

	// char array to store lines from input file
	char word[256];

	// check for 3 command line arguments
	if (argc != 3) {
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// open input file for reading
	in = fopen(argv[1], "r");
	if (in == NULL) {
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	// open output file for writing
	out = fopen(argv[2], "w");
	if (out == NULL) {
		printf("Unable to write to the file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	// create LinkedList Q
	LinkedList Q = newLinkedList();

	// create pointer for free()
	LinkedList* pQ = &Q;

	// iterate for each line
	while ( fgets(word, sizeof(word), in) ) {

		// if first letter is e, enqueue
		if (word[0] == 'e') {
			char number[256];
			int index = 0;
			int index2 = 0;
			int i = 0;

			// iterate until space is found
			for (i = 0; i < strlen(word); i++) {
				if (word[i] == ' ') {
					index = i + 1;
					break;
				}
			}

			int j = 0;

			// store the number to be enqueued into number[]
			for (j = index; j < strlen(word); j++) {
				number[index2] = word[j];
				index2++;
			}

			number[index2] = '\0';

			// call atoi(number) to enqueue the number to the list
			int num = atoi(number);

			// enqueue num and print to out
			enqueue(num, Q);
			fprintf(out, "enqueued %d\n", num);
			continue;

		} else {
			// if first letter is p, print
			if (word[0] == 'p') {

				// call print function
				print(out, Q);
				continue;
			} else {
				// else first letter is d and dequeue

				// n equal to dequeued number
				int n = dequeue(Q);

				// if n == -1 when the list is empty
				if (n == -1) {
					fprintf(out, "%s\n", "empty");
					continue;
				} else {
					fprintf(out, "%d\n", n);
					continue;
				}
			}
		}
	}

	// free LinkedList
	freeLinkedList(pQ);

	// close in and out files and return success
	fclose(in);
	fclose(out);
	return (EXIT_SUCCESS);


}
