#include <stdio.h>				//Ԥ���� 
#include <stdlib.h>
 

int main() {
								//"//"��ע�� 
	
	int a = 7/4;				//int ���� 
	int a2 = 7%4;				//%ģ���� 
	long int a1 = 1;			//������ 
	float b = 1232.6; 			//float ������->�����ȸ����� 
	double b1 = 2.12313;		//double ������->˫���ȸ����� 
	char c = 'a'; 				//char �ַ���
	
	printf("����:%d\n������:%.1f\n�ַ���:%c\n",a,b,c);
	printf("����:%d\t������:%.1f\t�ַ���:%c\n\n",a,b,c);
	printf("ģ��%d\n\n",a2);		//  \n ����  \t	tab	
	
	//printf("c=%c,(int)c=%d",c+1,(int)c+1);	//ǿ��ת�� 
	
			
//		printf("������a��a2��ֵ,�ö��Ÿ�����");
//		scanf("%d,%d",&a,&a1);				//���뺯�� �ǵ�&�Ĵ���� 
//		printf("a=%d a1=%d",a,a1);
	
//	c=getchar();			//ȡ�ַ� 
//	c=c+2;
//	putchar(c);				//����ַ� = printf("%c",c) 
	
	int dec = 1;
			
			//����/�Լ�
	dec += 1;				//dec=2
		printf("dec=%d\n",dec);
	dec -= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec *= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec/= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec %= 1;				//dec=dec%1 dec=0
		printf("dec=%d\n",dec);
			//����/�ݼ� 
	dec++;					//dec=dec+1 dec=1
		printf("dec=%d\n",dec);
	dec--;					//dec=0
		printf("dec=%d\n",dec);
	++dec;					//dec=1
		printf("dec=%d\n",dec);
	--dec;					//dec=0
		printf("dec=%d\n",dec);
		
	//�ӷ�������
	 
	int var1,var2;
	printf("�����������������üӺŸ���:");
	scanf("%d+%d",&var1,&var2);
	printf("%d+%d=%d",var1,var2,var1+var2); 
	
	
	
	
	return 0;
}
