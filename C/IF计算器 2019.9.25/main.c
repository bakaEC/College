#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
        printf("************************\n");
		printf("*** 1.+ ******** 2.- ***\n");
		printf("************************\n");
		printf("*** 3.* ******** 4./ ***\n");
		printf("************************\n");
		printf("������һ�����㷨��:\n");
	
	
	
	


	int a,b,f;
	char c;

	
	scanf("%c",&c);
		if(c == '1')
		{
		printf("�ӷ������������������ÿո����:");
		scanf("%d %d",&a,&b);
		f=a+b;
		printf("%d\n",f);
		}
		else if(c == '2')
		{
		printf("���������������������ÿո����:");
		scanf("%d %d",&a,&b);
		f=a-b;
		printf("%d\n",f);
		}
			 else if(c == '3')
				{
				printf("�˷������������������ÿո����:");
				scanf("%d %d",&a,&b);
				f=a*b;
				printf("%d\n",f);
				}
				  else if(c == '4')
					{
					printf("���������������������ÿո������");
					int x,y,z,g;
					scanf("%d %d",&x,&y);
					z=x / y;
					g=x % y;					
					printf("�̣�%d�ࣺ%d\n",z,g);
					}
					else printf("�Ƿ�����"); 

return 0;
}
