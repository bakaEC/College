#include <stdio.h>
void bubbleSort(int *arr,int length);
void insertSort(int* arr,int length);
void pPrint(int arr[]);
int main() {
	int arr[8] = { 1,5,4,8,3,7,2,6 };
	int length = sizeof(arr) / sizeof(int);
	
	//定义函数指针
	void(*pBubble)(int*,int) = bubbleSort;
	void(*pInsert)(int*,int) = insertSort;

	//调用指针函数
	(*pBubble)(arr, length);
	(*pInsert)(arr, length);
	
	system("pause");
	return 0;
}
//冒泡排序
void bubbleSort(int arr[],int length) {
	int temp = 0;
	for (int i = 0; i < length - 1; i++) {
		for (int j = 0; j < length - i - 1; j++) {
			if (arr[j + 1] > arr[j])
			{
				temp = arr[j + 1];
				arr[j + 1] = arr[j];
				arr[j] = temp;
			}
		}
	}
	printf_s("冒泡排序:");
	pPrint(arr,length);
}

//插入排序
void insertSort(int arr[],int length) {
	int tmp;
	int j;
	for (int i = 0; i < length; i++)
	{
		tmp = arr[i];
		for (j = i - 1; j >= 0 && arr[j] > tmp; j--)
		{
			arr[j + 1] = arr[j];
		}
		arr[j + 1] = tmp;
	}
	printf_s("插入排序:");
	pPrint(arr, length);
}
//输出数组函数
void pPrint(int arr[],int length) {
	
	for (int i = 0; i < length; i++)
	{
		printf("%d", arr[i]);
	}
	printf_s("\n");
}
