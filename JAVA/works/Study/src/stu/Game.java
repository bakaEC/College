package stu;

import java.util.Scanner;

public class Game {
    player p;
    Computer c;
    int count;
    Scanner input = new Scanner(System.in);
    

    //初始化
    public void initial(){
        p = new player();
        c = new Computer();
        count = 0;
    }

    //开始游戏
    public void startGame(){
        initial();
        System.out.println("-------------欢迎进入游戏--------------");
        System.out.println("\n\t\t****************************");
        System.out.println("\t\t******猜拳来了*********");
        System.out.println("\n\n请选择对方角色 1.孙权 2.曹操 3.刘备");
        int role = input.nextInt();
        switch(role){
            case 1 : 
                c.name = "孙权";
                break;
            case 2 : 
                c.name = "曹操";
                break;
            case 3 : 
                c.name = "刘备";
                break;
        }
        System.out.println("你选择了"+c.name);
        //开始游戏
        System.out.println("要开始吗？(y/n)");
        String con;
        con = input.next();
        int perFist;
        int comFist;
        while(con.equals("y")){
            perFist = p.choice();
            comFist = c.choice();
            //裁定
            if(perFist == 1 && comFist == 1 || perFist == 2 && comFist == 2 ||perFist == 3 && comFist == 3){
                System.out.println(c.name+"和您平局！");
            }else if(perFist == 1 && comFist ==3 || perFist == 2 && comFist ==1 || perFist == 3 && comFist ==2){
                System.out.println("您输给了"+c.name);
                c.score++;
            }else if(perFist == 1 && comFist == 2 || perFist == 2 && comFist ==3 || perFist == 3 && comFist ==1){
                System.out.println("您赢了"+c.name);
                p.score++;
            }
            count++;
            System.out.println("是否进行下一局？");
            con = input.next();
        }
        calcScore();
        showResult();
    }
    public int calcScore(){
        if (p.score == c.score){
            return 1;           //平局
        }else if (p.score > c.score){
            return 2;           //玩家赢
        }else return 3;         //电脑赢
    }

    public void showResult(){
        System.out.print("游戏结束,总共PK了"+count+1+"局,");
        switch(calcScore()){
            case 1 : System.out.print("双方平局，得分："+p.score);break;
            case 2 : System.out.print("恭喜您赢了！您的得分："+p.score);break;
            case 3 : System.out.print("很遗憾，您输给了"+c.name+"您的得分:"+p.score);break;
        }
        
    }
    
}