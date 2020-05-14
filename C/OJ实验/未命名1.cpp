#include<stdio.h>
int main(){
	int a,b;
	int md,c=0,day;
	for(day=1;day<=7;day++)
	{
		scanf("%d %d",&a,&b);
		if(a+b>8 && a+b>c)
		{c=a+b;
		 md=day;}
	}
	printf("%d",md);
	return 0; 	
}
