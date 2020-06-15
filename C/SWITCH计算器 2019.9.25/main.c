#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */


int main(int argc, char *argv[]) {
      	while(1){
	    printf("************************\n");
		printf("*** 1.+ ******** 2.- ***\n");
		printf("************************\n");
		printf("*** 3.* ******** 4./ ***\n");
		printf("************************\n");
		printf("请输入运算法则：\n");
	
	
	int a,b,c;
	char caculate;
	scanf("%c",&caculate);                  
	switch(caculate) {
	
	 case '+': 
	  	  	 printf("请输入两个加数，用空格隔开：\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n结果为:%d\n\n\n\n\n",a+b);
 	 		 break;
 	 case '-': 
	  	  	 printf("请输入两个减数，用空格隔开：\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n结果为:%d\n\n\n\n\n",a-b);
 	 		 break;	
 	 case '*': 
	  	  	 printf("请输入两个乘数，用空格隔开：\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n结果为:%d\n\n\n\n\n",a*b);
 	 		 break;
 	 case '/': 
	  	  	 printf("请输入两个除数，用空格隔开：\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n商为:%d\n余数为:%d\n\n\n\n\n",a/b,a%b);
 	 		 break;
	 default: printf("非法输入"); 
             break;
   			 getchar();
	
	}	}	 
	return 0;
}
