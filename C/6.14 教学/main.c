#include <stdio.h>
#include <stdlib.h>


int main() {
	int a = 0;
//	scanf("%d",&a);
//	
//	if(a == 2){
//		printf("a=2");	
//	}else{
//		printf("a!=2");
//	}						
//	
//	/*
//	算术判断：== (>=) <= != > <
//	逻辑判断： 或：||  与：&&  非：！ 
//	*/
//	
//	printf("\n");
//	
//	if(a == 3){
//		printf("a=3");
//	}else if(a == 4){
//		printf("a=4");
//	}else{
//		printf("a!=3||4");
//	}
//	
//	printf("\n");
//	
//	if(a > 5){
//		if(a > 6){
//			printf("a>5&&a>6");			//双重判断 
//		}
//		if(a > 7){
//			printf("a>5&&a>7");
//		}
//	}
//	
//	printf("\n");
//	
//	if(a>8 && a>9 && a>10){
//			
//		printf("a>8&&a>9&&a>10");
//		
//	} 
//	
//	printf("\n");
//	
//	if(a>11 || a>12 || a>13){
//		
//		printf("a>11||a>12||a>13");
//		
//	}
//	
//	printf("\n");
//	
//	if(a!=14){
//		printf("a!=14");
//	}
//	
//	printf("\n");
//	
//	switch(a){				//switch语句 
//		case 1: {			//if(a==1)
//			printf("a = 1"); 
//			break;
//		}
//		case 2:{
//			printf("a = 2");
//			break;
//		}
//		case 3:{
//			printf("a = 3");
//			break;
//		}
//	}
	/*
					课后作业
		利用switch结构做一个四则运算计算器。
		 
	*/
	
	float b,b1;
	char c;
	
	
	printf("请输入您的四则运算符号：+-*/");
	scanf("%c",&c);	//输入符号
	printf("请输入您的两个操作数") ;
	scanf("%f,%f",&b,&b1);	//输入操作数
	
	switch(c){
		case '/':{
			printf("%d/%d=%.2f",(int)b,(int)b1,b/b1);
			break;
		}
	}

	
	
	return 0;
}
