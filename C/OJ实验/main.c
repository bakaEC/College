#include <stdio.h>
#include <string.h>
int main() {
	char a[333]={'1'},b[333]={'1'},c;
	int i;
	for(i=0;i<333;i++){
	c=getchar();
	if(c!=' ') strcat(a,c);
	else if(c==' ') i++;
		 if(c=='\n') break;
	
	}
	printf("%c",a[0]);
	return 0;
}
