# 2020/6/12 教学内容
1. C语言的基本数据类型、表达式及表达式运算

- 数据类型的概念

- 常量

- 变量

- 运算符与其优先级和结合性

- 表达式的概念、分类和求值运算

2. 顺序结构

（1）基本语句

	①C语句的分类 ②表达式语句 ③赋值语句 ④复合语句和空语句

（2）输入输出函数的调用

	①格式转换的输入输出函数（scanf/printf）
	字符的输入输出函数（getchar/putchar）	
	
```
#include <stdio.h>				//预处理 
#include <stdlib.h>
 

int main() {
								//"//"是注释 
	
	int a = 7/4;				//int 整型 
	int a2 = 7%4;				//%模运算 
	long int a1 = 1;			//长整型 
	float b = 1232.0; 			//float 浮点型->单精度浮点数 
	double b1 = 2.12313;		//double 长浮点->双精度浮点数 
	char c = 'a'; 				//char 字符型
	
	printf("整型:%d\n浮点数:%.1f\n字符型:%c\n",a,b,c);
	printf("整型:%d\t浮点数:%.1f\t字符型:%c\n\n",a,b,c);
	printf("模：%d\n\n",a2);		//  \n 换行  \t	tab			
//		printf("请输入a和a2的值,用逗号隔开：");
//		scanf("%d,%d",&a,&a1);				//输入函数 记得&寄存符号 
//		printf("a=%d a1=%d",a,a1);
	
//	c=getchar();			//取字符 
//	c=c+2;
//	putchar(c);				//输出字符 = printf("%c",c) 
	
	int dec = 1;
			
			//自增/自减
	dec += 1;				//dec=2
		printf("dec=%d\n",dec);
	dec -= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec *= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec/= 1;				//dec=1 
		printf("dec=%d\n",dec);
	dec %= 1;				//dec=dec%1 dec=0
		printf("dec=%d\n",dec);
			//递增/递减 
	dec++;					//dec=dec+1 dec=1
		printf("dec=%d\n",dec);
	dec--;					//dec=0
		printf("dec=%d\n",dec);
	++dec;					//dec=1
		printf("dec=%d\n",dec);
	--dec;					//dec=0
		printf("dec=%d\n",dec);
		
			//加法计算器
	 
	int var1,var2;
	printf("请输入两个加数，用加号隔开:");
	scanf("%d+%d",&var1,&var2);
	printf("%d+%d=%d",var1,var2,var1+var2); 
	
	
	
	
	return 0;
}
```