#include <stdio.h>
#include <math.h>
int main() {
	int i,j=0,c;
	char x;
	long int a[10]={0},b[10]={0};

		scanf("%d%d%d%d%d%d%d%d%d%d",&a[i],&a[i+1],&a[i+2],&a[i+3],&x,&a[i+4],&a[i+5],&a[i+6],&a[i+7],&a[i+8],&a[i+9]);		
	

	for(i=0;i<9;i++){
		printf("%d\n",a[i]);
	}
//	for(j=0;j<9;j++){
//		c+=(b[j]-'0')*(j+1);
//	}
//	c%=11;
//	int d=a[12]-'0';
//	if(c==d){
//		printf("Right");	
//	}
//	else {
//	a[12]=(char)c;			//就这一步 
//	for(i=0;i<13;i++){
//		printf("%c",a[i]);		
//}
//	}
	return 0;
}
