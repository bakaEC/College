#include <stdio.h>
#include <math.h> 
int main() {
	int in,sep;
	scanf("%d",&in);
	printf("%d=",in);
	for(sep=2;sep<=sqrt(in);sep++){
		if(in%sep==0&&in/sep!=1){
			printf("%d*",sep);
			in/=sep;
			sep=2;
		}
	}
	printf("%d",in);
	return 0;
}
