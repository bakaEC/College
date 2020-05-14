#include <stdio.h>

int main(){
	char a[13]={},end;
	long int i,sum=0;
    scanf("%c-%c%c%c-%c%c%c%c%c-%c",&a[1],&a[2],&a[3],&a[4],&a[5],&a[6],&a[7],&a[8],&a[9],&end);
	for(i=1;i<=9;i++){
		sum+=(a[i]-48)*i;
	}
	sum%=11;

	if(sum==10&&end=='X'){
		printf("Right");
		return 0;
	}
	if(sum==end-48){
		printf("Right");
	}
	else{
		if(sum==10){
			printf("%c-%c%c%c-%c%c%c%c%c-X",a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9]);
		}
		else printf("%c-%c%c%c-%c%c%c%c%c-%d",a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9],sum);
	} 

	
return 0;
}
