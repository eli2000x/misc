
#ifndef QUEUE_H_
#define QUEUE_H_

#include <stdio.h>

// typedef statement, linkedlist is linkedlistobj pointer
typedef struct LinkedListObj* LinkedList;

// typedef statement, node is nodeobj pointer
typedef struct NodeObj* Node;

// constructor for creating newnode
Node newNode(int x);

// constructor for creating newlinkedlist
LinkedList newLinkedList();

// free Node
void freeNode(Node* pS);

// free LinkedList
void freeLinkedList(LinkedList* pS);

// add element to list
void enqueue(int n, LinkedList S);

// return number at head of list and delete
int dequeue(LinkedList S);

// print elements of linkedlist in order
void print(FILE* out, LinkedList S);


#endif /* QUEUE_H_ */
