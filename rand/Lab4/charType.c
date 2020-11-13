// Name: Erik Li
// CruzID: ersli
// Role: Lab 4 - classify chars in C
// File Name: charType.c

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>

// function that modifiers points and classifies chars in input string
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	int i = 0;
	int acount = 0;
	int dcount = 0;
	int pcount = 0;
	int wcount = 0;

	// while loop to assign chars to appropriate array of chars
	while (i < strlen(s)) {
		// handles alphabetic
		if (isalpha(s[i]) != 0) {
			a[acount] = s[i];
			acount++;
			i++;
			continue;
		} else {
			// handles digits
			if (isdigit(s[i]) != 0) {
				d[dcount] = s[i];
				dcount++;
				i++;
				continue;
			} else {
				// handles punctuation
				if (ispunct(s[i]) != 0) {
					p[pcount] = s[i];
					pcount++;
					i++;
					continue;
				} else {
					// handles spaces
					if (isspace(s[i]) != 0) {
						w[wcount] = s[i];
						wcount++;
						i++;
						continue;
					}
				}
			}
		}
		i++;
	}

	// sets last relevant element to null
	a[acount] = '\0';
	d[dcount] = '\0';
	p[pcount] = '\0';
	w[wcount] = '\0';
}

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

	// count for number of lines
	int count = 1;
	int i = 0;

	while (fgets(word, sizeof(word), in)) {

		// allocate space for pointers for each char type
		char* a = calloc(256, sizeof(char));
		if( a==NULL ){
		   fprintf(stderr, "malloc failed\n");
		   exit(EXIT_FAILURE);
		}

		char* d = calloc(256, sizeof(char));
		if( d==NULL ){
		   fprintf(stderr, "malloc failed\n");
		   exit(EXIT_FAILURE);
		}

		char* p = calloc(256, sizeof(char));
		if( p==NULL ){
		   fprintf(stderr, "malloc failed\n");
		   exit(EXIT_FAILURE);
		}

		char* w = calloc(256, sizeof(char));
		if( w==NULL ){
		   fprintf(stderr, "malloc failed\n");
		   exit(EXIT_FAILURE);
		}

		// call extract_chars function on pointers
		extract_chars(word, a, d, p, w);
		fprintf(out, "line %d contains:\n", count);
		count++;


		// print to out the appropriate elements in each array
		if (strlen(a) == 1) {
			fprintf(out, "1 alphabetic character: %c\n", a[0]);
		} else {
			fprintf(out, "%d alphabetic characters: ", (int)strlen(a));
			for (i = 0; i < strlen(a); i++) {
				fprintf(out, "%c", a[i]);
			}
			fprintf(out, "\n");
		}

		if (strlen(d) == 1) {
			fprintf(out, "1 numeric character: %c\n", d[0]);
		} else {
			fprintf(out, "%d numeric characters: ", (int)strlen(d));
			for (i = 0; i < strlen(d); i++) {
				fprintf(out, "%c", d[i]);
			}
			fprintf(out, "\n");
		}

		if (strlen(p) == 1) {
			fprintf(out, "1 punctuation character: %c\n", p[0]);
		} else {
			fprintf(out, "%d punctuation characters: ", (int)strlen(p));
			for (i = 0; i < strlen(p); i++) {
				fprintf(out, "%c", p[i]);
			}
			fprintf(out, "\n");
		}

		if (strlen(w) == 1) {
			fprintf(out, "1 whitespace character: %c\n", w[0]);
		} else {
			fprintf(out, "%d whitespace characters: ", (int)strlen(w));
			for (i = 0; i < strlen(w); i++) {
				fprintf(out, "%c", w[i]);
			}
			fprintf(out, "\n");
		}


		// free pointers and set to null to avoid memory leaks
		free(a);
		a = NULL;
		free(d);
		d = NULL;
		free(p);
		p = NULL;
		free(w);
		w = NULL;


	}
	// close in and out files and return success
	fclose(in);
	fclose(out);
	return (EXIT_SUCCESS);

}

