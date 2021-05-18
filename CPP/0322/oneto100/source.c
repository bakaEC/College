#include <stdio.h>
#include <math.h>
int main() {
    int i, j;
    for (i = 1; i <= 100; i++) {
        for (j = 2; j < i; j++) {
            if (i % j == 0)
                break;
        }
        if (i == j) {
            printf("%d ", i);
        }
        i++;
    }
    printf("\n");
    int m = 1;
    do {
        int n = 2;
        do{
            if (m%n==0){
                break;
            }
            n++;
        } while (n < m);
       
        m +=2;
    } while (m <= 100);
	return 0;
}
