#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
        printf("************************\n");
		printf("*** 1.+ ******** 2.- ***\n");
		printf("************************\n");
		printf("*** 3.* ******** 4./ ***\n");
		printf("************************\n");
		printf("请输入一个运算法则:\n");
	
	
	
	


	int a,b,f;
	char c;

	
	scanf("%c",&c);
		if(c == '1')
		{
		printf("加法，请输入两个数并用空格隔开:");
		scanf("%d %d",&a,&b);
		f=a+b;
		printf("%d\n",f);
		}
		else if(c == '2')
		{
		printf("减法，请输入两个数并用空格隔开:");
		scanf("%d %d",&a,&b);
		f=a-b;
		printf("%d\n",f);
		}
			 else if(c == '3')
				{
				printf("乘法，请输入两个数并用空格隔开:");
				scanf("%d %d",&a,&b);
				f=a*b;
				printf("%d\n",f);
				}
				  else if(c == '4')
					{
					printf("除法，请输入两个数并用空格隔开：");
					int x,y,z,g;
					scanf("%d %d",&x,&y);
					z=x / y;
					g=x % y;					
					printf("商：%d余：%d\n",z,g);
					}
					else printf("非法输入"); 

return 0;
}
