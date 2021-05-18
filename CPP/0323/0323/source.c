#include <stdio.h>
int main() {
	int a[10];
	int i;
	for (i = 0; i < 10;i++) {
		printf("ÇëÊäÈëÊý×Ö£º");
		scanf_s("%d", &a[i]);
	}

	for (int j = 0; j < sizeof(a)/sizeof(int); j++)
	{
		for (int m = j; m < 10; m++)
		{
			if (a[m] > a[j]) {
				int temp = a[m];
				a[m] = a[j];
				a[j] = temp;
			}
		}
	}
	for (int n = 0; n < 10; n++) {
		printf_s("%d ", a[n]);
	}
	return 0;
}

