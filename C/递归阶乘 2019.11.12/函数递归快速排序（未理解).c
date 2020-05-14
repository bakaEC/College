#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
unsigned long fact (unsigned long n)
{
	if(n<=1) return 1;
	else return fact(n-1)*n;
}
int main() {
	int a;
	scanf("%d",&a);
	printf("%d",fact(a));h
	return 0;
}
