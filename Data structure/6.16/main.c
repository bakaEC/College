#include<stdio.h>

void move (int n,char x,char y,char z){
	if(n==1){
		printf("%c-->%c\n",x,z);
	}else{
		move(n-1,y,x,z);
	}
}
int main(){
	int n;
	printf("ÇëÊäÈë²ãÊı£º");
	scanf("%d",&n);
	move(n,'X','Y','Z');
	return 0;
}
