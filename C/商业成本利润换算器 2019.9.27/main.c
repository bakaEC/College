#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	long per=0,cost=0,gain=0,sum=0,s=0,i=0;
	printf("���������ۣ�\n"); 
	scanf("%d",&per);
	printf("�����뵥���ɱ���\n"); 
	scanf("%d",&cost);
	gain = per - cost;
	printf("���ĵ���ӯ����%d\n",gain);
	printf("\n\n������Ŀ��ӯ����\n"); 
	scanf("%d",&s);
	while(sum < s)
	{
		sum += gain;
		i++; 
	}
	printf("��Ŀ��ӯ�����������Ʒ��%d,�������С�ܼ�%d\n",i,sum);
	int b=0;
	printf("\n\n��������������������Ŀ��ӯ�����ö��Ÿ�����\n");
	scanf("%d,%d",&b,&s); 
	sum = gain*b-s;
	if (sum>0)
	   printf("��ӯ��%dԪ",sum);
    else
	   printf("��ӯ��%dԪ",sum);
    
    
	 
	 
	return 0;
}
