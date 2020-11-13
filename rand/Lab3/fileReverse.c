// Name: Erik Li
// CruzID: ersli
// Role: Lab 3 - Reverse lines of an input file in C
// File Name: fileReverse.c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// takes a pointer to a string as input, changes content of variable pointed at
void stringReverse(char* s) {
	// char[] temp as a copy of s[]
	char temp[256];
	int i;
	int k;
	// copy contents of s[] into input
	for (i = 0; i < strlen(s); i++) {
		temp[i] = s[i];
	}
	// set beginning elements of s to corresponding ending elements of input
	for (k = 0; k < strlen(s); k++) {
		s[k] = temp[i - 1 - k];
	}

};

int main(int argc, char* argv[]) {
	// file handle for input
	FILE* in;
	// file handle for output
	FILE* out;
	// char array to store words from input file
	char word[256];
	// checks if 3 command line arguments
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
	// read words from input file, print reversed word on separate lines to output file
	while ( fscanf(in, " %s", word) != EOF ) {
		stringReverse(word);
		fprintf(out, "%s\n", word);
	}
	// close input and output files
	fclose(in);
	fclose(out);

	return (EXIT_SUCCESS);
}
