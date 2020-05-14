#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
	int L[20]={};
	int i,val,pos;
	printf("已有的数组:\n"); 
	for(i=0;i<19;i++){
		L[i]=i+1;
		printf("%d ",L[i]);
	}
	printf("\n请输入要插入的数字和位置");
		scanf("%d %d",&val,&pos);
		for(i=20;i>=pos;i--){
			L[i]=L[i-1];		//从最后一位向后依次赋值（移位） 
		}
		L[pos]=val;				//将插入点赋值 
	printf("插入后的数组：\n");
	for(i=0;i<20;i++){
		printf("%d ",L[i]);
	}
	return 0;
}
