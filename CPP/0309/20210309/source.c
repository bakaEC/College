#include <stdio.h>
int main() {
	long long ln = 123123L;				//-2^63~2^63-1
	unsigned long long ln2 = 123123L;	//0~2^64
	int n = 789;						//-2^31~2^31-1
	unsigned n2 = 123;					//0~2^32
	short sn = 35;						//-2^15~2^15-1
	unsigned short sn2 = 25;			//0~2^16
	printf("%d", n);

	float fl = 1.56f;					//-2^31~2^31-1,dot3.4*e^-32
	//unsigned  fl2 = 123.2f;				//2^32,dot1.4*e^-32
	double dl = 3.14;					//-2^31~2^31-1,dot1.79*e^-64
	long double dl2 = 3.1415926;		//-2^63~2^63-1,dot1.79*e^-96

	printf_s("请输入一个单精度浮点数\n");
	scanf_s("%f", fl);
	printf("%f", fl);
}