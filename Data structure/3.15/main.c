#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
	int n,i,j;
	printf("���������:"); 
	scanf("%d",&n);
	int L[n];
	for(i=1;i<=n-1;i++){
		L[i]=i;				//��ֵ��Ȼ�� 
	}
	printf("���е�����(��1���ÿռ�)��");
	for(i=1;i<=n-1;i++){
		printf("%d\t",L[i]);
	}
	for(i=1;i<=n-1;i++){
		L[n]=L[i];				//�ӵ�һλ����ֵ��ֵ�������ռ� 
		L[i]=L[n-i];			//�����һλ��ʼ����ֵ��ֵ��iλ 
		L[n-i]=L[n];
		printf("\n%d\n",L[n]);			//�������ռ��ֵ��ֵ����һ�������� 
	}
	printf("\n���ú�����飺");
	for(i=1;i<=n;i++){
	printf("%d\t",L[i]); 
	}
	 return 0;
}
