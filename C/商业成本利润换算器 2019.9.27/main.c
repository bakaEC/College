#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	long per=0,cost=0,gain=0,sum=0,s=0,i=0;
	printf("请输入卖价：\n"); 
	scanf("%d",&per);
	printf("请输入单件成本：\n"); 
	scanf("%d",&cost);
	gain = per - cost;
	printf("您的单件盈利：%d\n",gain);
	printf("\n\n请输入目标盈利：\n"); 
	scanf("%d",&s);
	while(sum < s)
	{
		sum += gain;
		i++; 
	}
	printf("在目标盈利内满足的商品数%d,满足的最小总价%d\n",i,sum);
	int b=0;
	printf("\n\n请输入已卖出的数量和目标盈利，用逗号隔开：\n");
	scanf("%d,%d",&b,&s); 
	sum = gain*b-s;
	if (sum>0)
	   printf("多盈利%d元",sum);
    else
	   printf("少盈利%d元",sum);
    
    
	 
	 
	return 0;
}
