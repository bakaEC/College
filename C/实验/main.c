#include <stdio.h>

int main() {
	char input[12];

	for(int i=0;i<2;i++){
		scanf("%c",&input[i]);
	}
	char a=&input[1];
	printf("%c",a);
	return 0;
}
