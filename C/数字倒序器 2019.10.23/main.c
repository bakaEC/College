#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int i=0,A[5]={0};
	for(;i<=4;i++){
		system("cls");
		printf("请输入第%d个数:",i+1);
		scanf("%d",&A[i]);
	} 
	printf("倒序为：");
	for(i-=1;i>=0;i--){
		printf("%d",A[i]);
	} 
	return 0;
}
