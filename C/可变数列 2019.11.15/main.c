#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	
	int i,j,k;
	int b[999]={0};
	for (i=0;;i++)
	{
	k=i+1;
	int a[k];
	printf("\n请输入要向数组中加入的数:");
	scanf("%d",&a[i]);
	for(j=i;j<i+1;j++)
	{
		b[j]=a[i];
	}
	printf("新的数组为:");
	for(j=0;j<(sizeof a)/4;j++)
	{
		
		printf("%d",b[j]);

	}
	printf("\n数组a的长度:%d\n",(sizeof a)/4); 
	}
	return 0;
	
}
