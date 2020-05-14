#include <stdio.h>
#include <string.h>
int main() {
	char a[51];
	int i,j;
	scanf("%d%s",&j,&a);
	for(i=0;a[i]!='\0';i++){
		printf("%c",(a[i]-'a'+j)%26+'a');
	} 
	return 0;
}








