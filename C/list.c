#include <stdio.h>
int main(){
    int name[26];
    FILE *fp=fopen("data.txt","w+");
    for (int i = 1; i <= 26; i++)    
    {
        name[i]=i*10000;
    }
    for (int j = 1; j <= sizeof(name); j++)
    {
        fprintf(fp,"%d,",name[j]);
        
    }
    
    fclose(fp);
    return 0;
}
