
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#include "List.h"

int main(int argc, char* argv[]) {

	// files for input and output
	FILE* in;
	FILE* input;
	FILE* out;

	// char array to store lines from input file
	char word[1000];
	char w[1000];

	// check for command line arguments != 3
	if (argc != 3) {
		printf("Command line arguments != 3\n");
		exit(EXIT_FAILURE);
	}

	// open input file for reading
	input = fopen(argv[1], "r");
	if (input == NULL) {
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	// open output file for writing
	out = fopen(argv[2], "w");
	if (out == NULL) {
		printf("Unable to write to the file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	// create new list and append first item
	List L = newList();
	append(L, 0);

	// create array of strings to store input
	char arr[1000][256];
	int num = 0;

	// store lines into string array
	while(fgets(w, 1000, input) != NULL) {
		for (int k = 0; k < strlen(w); k++) {
			arr[num][k] = w[k];

		}
		arr[num][strlen(w)] = '\0';
		num++;
	}


	// close and open new file for reading
	fclose(input);
	in = fopen(argv[1], "r");
	if (in == NULL) {
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	int i = 1;
	int wow = 0;

	// perform "sorting" for list
	while(fgets(word, 1000, in) != NULL) {
		if (wow == 0) {
			wow = 1;
			continue;
		}
		moveFront(L);
		int j = 0;

		int broke = 0;

		while(j < length(L)) {
			if (strcmp(word, arr[get(L)]) <= 0) {
				insertBefore(L, i);
				broke = 1;
				i++;
				break;
			} else {
				if (index(L) < 0 || get(L) == back(L)) {
					append(L, i);
					broke = 1;
					i++;
					break;
				}
				moveNext(L);
			}
			j++;
		}
		if (broke == 1) {
			continue;
		}
		insertAfter(L, i);
		i++;
	}

	// print list to out file
	moveFront(L);
	for (int a = 0; a < length(L); a++) {
		if (get(L) == length(L) - 1) {
			fprintf(out, "%s\n", arr[get(L)]);
		} else {
			fprintf(out, "%s", arr[get(L)]);
		}
		moveNext(L);
	}

	// free list and close files
	freeList(&L);
	fclose(in);
	fclose(out);

}
