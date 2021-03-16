#include <stdio.h>
int main() {

	long long ln = 123123L;//-2^63~2^63-1
	printf_s("请输入长长整形\n");
	scanf_s("%lld", &ln);
	printf_s("%lld", ln);
	unsigned long long ln2 = 123123L;	//0~2^64
	printf_s("请输入无符号长长整形\n");
	scanf_s("%lld", &ln2);
	printf_s("%lld", ln2);
	int n = 789;						//-2^31~2^31-1
	printf_s("请输入整形\n");
	scanf_s("%d", &n);
	printf_s("%d", n);
	unsigned int n2 = 123;					//0~2^32
	printf_s("请输入无符号整形\n");
	scanf_s("%d", &n2);
	printf_s("%d", n2);
	short sn = 35;						//-2^15~2^15-1
	printf_s("请输入短整形\n");
	scanf_s("%hd", &sn);
	printf_s("%hd", sn);
	unsigned short sn2 = 25;			//0~2^16
	printf_s("请输入无符号短整形\n");
	scanf_s("%hd", &sn2);
	printf_s("%hd", sn2);
	float fl = 1.56f;					//-2^31~2^31-1,dot3.4*e^-32
	printf_s("请输入浮点型\n");
	scanf_s("%f", &fl);
	printf_s("%f", fl);
	unsigned fl2 = 0;				//2^32,dot1.4*e^-32
	printf_s("请输入无符浮点型\n");
	scanf_s("%d", &fl2);
	printf_s("%d", fl2);
	double dl = 3.14;					//-2^31~2^31-1,dot1.79*e^-64
	printf_s("请输入双精度浮点型\n");
	scanf_s("%lf", &dl);
	printf_s("%lf", dl);
	long double dl2 = 3.1415926;		//-2^63~2^63-1,dot1.79*e^-96
	printf_s("请输入长浮点型\n");
	scanf_s("%lf", &dl2);
	printf_s("%lf", dl2);
	char chr;
	printf_s("请输入字符型\n");
	scanf_s("%c", &chr,20);
	printf_s("%c", chr);
	unsigned char chr2;
	printf_s("请输入无符号字符型\n");
	scanf_s("%c", &chr2,20);
	printf_s("%c", chr2);
	system("pause");
	return 0;
}