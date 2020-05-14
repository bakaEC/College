#include <stdio.h>
#include <stdlib.h>
#include <windows.h>


void menu();
void reset(char chess[3][3]);
void cl();
void printchess(char chess[3][3]);
void act1(char chess[3][3]);
void act2(char chess[3][3]);
int full(char chess[3][3]);
char judge(char chess[3][3]);
 
int main() {
	char chess[3][3];
	reset(chess);
	menu();
	printchess(chess);
	while(judge(chess)){
		act1(chess);
		cl();
		printchess(chess);
		if(full(chess)){
			printf("平局");
			break;
		}
		if(judge(chess)=='X'){
			printf("玩家X赢了");
			break;	
		}
		act2(chess);
		if(judge(chess)=='O'){
			printf("玩家O赢了"); 
			break;
		}
		cl();
		printchess(chess);	
	}

	return 0;
}
void reset(char chess[3][3]){
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			chess[i][j]=' ';
		}
	}
}
void printchess(char chess[3][3]) {
	printf("-------------\n");
	
	for(int i=0;i<3;i++){
		printf("|%c  | %c | %c |\n",chess[i][0],chess[i][1],chess[i][2]);
		if(i<2){
			printf("|---+---+---|\n");
		}
	}
	printf("-------------\n");
}
void menu(){
	int i;
	printf("\n**********************\n"
		   "*井字棋游戏 准备中...*\n"
		   "**********************\n  ");
	sleep(1);
	cl();
}
void cl(){
	system("cls");
}
void act1(char chess[3][3]){
	int x = 0;
	int y = 0;
	while (1)
	{
		printf("请输入x,y坐标:");
		scanf("%d %d", &x, &y);
		if (chess[--x][--y] ==' ')
		{
			 chess[x][y] = 'X';
			break;
		}
		else
		{
			printf("已有棋子，请重新选择。\n");
		}
	}
}

void act2(char chess[3][3]){
	int x = 0;
	int y = 0;
	while (1)
	{
		printf("请输入x,y坐标:");
		scanf("%d %d", &x, &y);
		if (chess[--x][--y] == ' ')
		{
			chess[x][y] = 'O';
			break;
		}
		else
		{
			printf("已有棋子，请重新选择。\n");
		}
	}
}
int full(char chess[3][3])
{
	for (int i=0;i<3;i++)
	{
		for (int j=0; j<3; j++)
		{
			if (chess[i][j]==' ')
				return 0;
		}	
	}
	return 1;	
}
char judge(char chess[3][3]){

	for (int i=0;i<3;i++)
	{
		if ((chess[i][0]==chess[i][1])&&(chess[i][1]==chess[i][2])&&(chess[i][0] != ' '))
			return chess[i][0];
		else if ((chess[0][i]==chess[1][i])&&(chess[1][i]==chess[2][i])&&(chess[0][i] != ' '))
			return chess[0][i];
		else if ((chess[0][0]==chess[1][1])&&(chess[1][1]==chess[2][2])&&(chess[1][1] != ' '))
			return chess[1][1];
		else if ((chess[0][2]==chess[1][1])&&(chess[1][1]==chess[2][0])&&(chess[1][1] != ' '))
			return chess[1][1];
	}
	return ' ';
}	


