#include <stdio.h>
#include <stdlib.h>
typedef int LinkList;
typedef int Node;
typedef struct Node
{
    char data;
    struct node *Next;
}Linklist;
void DeleteSame(LinkList L){
	Node *p,*q,*r;
	p=L->Next;
	q=p->Next;
	while(p->Next!=NULL){
		while(q){
			r=q;
			if(q->Data == p->Data){
				r->Next=q->Next;
				free(q);
				q=r->Next;
			}
			else q=q->Next;
		}
		p=p->Next
	}

























int main() {
	
	return 0;
}
