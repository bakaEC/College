#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
	int n,i,j;
	printf("请输入表长度:"); 
	scanf("%d",&n);
	int L[n];
	for(i=1;i<=n-1;i++){
		L[i]=i;				//赋值自然数 
	}
	printf("已有的数组(含1可用空间)：");
	for(i=1;i<=n-1;i++){
		printf("%d\t",L[i]);
	}
	for(i=1;i<=n-1;i++){
		L[n]=L[i];				//从第一位起将数值赋值给辅助空间 
		L[i]=L[n-i];			//从最后一位起开始将数值赋值给i位 
		L[n-i]=L[n];
		printf("\n%d\n",L[n]);			//将辅助空间的值赋值给上一步操作数 
	}
	printf("\n倒置后的数组：");
	for(i=1;i<=n;i++){
	printf("%d\t",L[i]); 
	}
	 return 0;
}
