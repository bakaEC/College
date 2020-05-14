#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int i;
	int a[] = {3,5,7,8,0,0};

	for(i=5;i>2;i--)
	{
		a[i]=a[i-1];
	}
		a[3]=6;
	for(i=0;i<6;i++)
	printf("%d\t",a[i]);
	return 0;
}
