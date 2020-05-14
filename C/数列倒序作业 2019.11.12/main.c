#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int a[5]={3,5,7,8,0};
	int i,temp;
	for (i=0;i<5/2;i++)
		{
			temp=a[i];
			a[i]=a[4-i];
			a[4-i]=temp;
		}
	for(i=0;i<5;i++)
	{
		printf("%d\t",a[i]);
	}
	
	return 0;
}
