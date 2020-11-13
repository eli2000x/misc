// Name: Erik Li
// CruzID: ersli
// Role: Lab5 - implementation file for queue
// File Name: queue.c

#include "queue.h"
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>
#include <assert.h>

// LinkedList has field head
typedef struct LinkedListObj {
	Node head;
} LinkedListObj;

// Node has fields item next
typedef struct NodeObj {
	int item;
	struct NodeObj* next;
} NodeObj;

// set item to input num and next to null
Node newNode(int x) {
	Node N = malloc(sizeof(NodeObj));
	assert( N != NULL );
	N->item = x;
	N->next = NULL;
	return N;
}

// set head to null
LinkedList newLinkedList() {
	LinkedList L = malloc(sizeof(LinkedListObj));
	assert( L != NULL);
	L->head = NULL;
	return L;
}

// free Node and set pointer to null
void freeNode(Node* pS) {
	if (pS != NULL && *pS != NULL) {
		free(*pS);
		*pS = NULL;
	}
}

// free LinkedList and set pointer to null
void freeLinkedList(LinkedList* pS) {
	if (pS != NULL && *pS != NULL) {
		free(*pS);
		*pS = NULL;
	}
}

// set head to item if null, else add to end of list
void enqueue(int n, LinkedList S) {
	Node N = newNode(n);
	Node current = S->head;

	// head is null, set head to item
	if (current == NULL) {
		S->head = N;
		return;
	}

	// traverse to end of list, set next to new item
	while(current->next != NULL) {
		current = current->next;
	}
	current->next = N;
}

// delete first item from list and return it
int dequeue(LinkedList S) {
	Node current = S->head;

	// if head is null, return -1, indicating empty
	if (current == NULL) {
		return -1;
	}

	// return first element and set head to head.next
	int num = current->item;
	S->head = current->next;
	return num;
}

// print out elements in linkedlist in order
void print(FILE* out, LinkedList S) {
	Node current = S->head;

	// if head is null, skip line
	if (current == NULL) {
		fprintf(out, "\n");
		return;
	}

	// else print elements in order to out file
	while (current != NULL) {
		fprintf(out, "%d ", current->item);
		current = current->next;
	}
	fprintf(out, "\n");
}
