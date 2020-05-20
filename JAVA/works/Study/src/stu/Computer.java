package stu;
import java.util.Random; 
public class Computer {

    String name = "电脑";
    int score = 0;

    /**
     * 计算机类
     * @author bakaEC
     * 阶段二任务
     */
    public int choice(){
        //产生随机数
        Random random = new Random();
        int show=random.nextInt(3)+1;
        //输出结果
        switch(show){
            case 1: System.out.println("电脑出石头");
            break;
            case 2: System.out.println("电脑出剪刀");
            break;
            case 3: System.out.println("电脑出布");
            break;
        }
        return show;
    }
    
}