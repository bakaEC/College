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
		printf("���������㷨��\n");
	
	
	int a,b,c;
	char caculate;
	scanf("%c",&caculate);                  
	switch(caculate) {
	
	 case '+': 
	  	  	 printf("�����������������ÿո������\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n���Ϊ:%d\n\n\n\n\n",a+b);
 	 		 break;
 	 case '-': 
	  	  	 printf("�����������������ÿո������\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n���Ϊ:%d\n\n\n\n\n",a-b);
 	 		 break;	
 	 case '*': 
	  	  	 printf("�����������������ÿո������\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n���Ϊ:%d\n\n\n\n\n",a*b);
 	 		 break;
 	 case '/': 
	  	  	 printf("�����������������ÿո������\n");
 	 		 scanf("%d %d",&a,&b);
 	 		 printf("\n��Ϊ:%d\n����Ϊ:%d\n\n\n\n\n",a/b,a%b);
 	 		 break;
	 default: printf("�Ƿ�����"); 
             break;
   			 getchar();
	
	}	}	 
	return 0;
}
