#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
	int L[20]={};
	int i,val,pos;
	printf("���е�����:\n"); 
	for(i=0;i<19;i++){
		L[i]=i+1;
		printf("%d ",L[i]);
	}
	printf("\n������Ҫ��������ֺ�λ��");
		scanf("%d %d",&val,&pos);
		for(i=20;i>=pos;i--){
			L[i]=L[i-1];		//�����һλ������θ�ֵ����λ�� 
		}
		L[pos]=val;				//������㸳ֵ 
	printf("���������飺\n");
	for(i=0;i<20;i++){
		printf("%d ",L[i]);
	}
	return 0;
}
