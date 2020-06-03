package stu;

import java.util.Scanner;

public class player {
    /**
     * 用户类 阶段1任务
     * 
     * @author bakaEC
     * @version 1.0
     */
    int score = 0;

    public int choice() {

        Scanner input = new Scanner(System.in);
        /**
         * 出拳
         * 
         * @return 出拳结果：1.石头 2.剪刀 3.布
         */
        System.out.println("请输入出拳：1.石头 2.剪刀 3.布");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("你出石头");
                break;
            case 2:
                System.out.println("你出剪刀");
                break;
            case 3:
                System.out.println("你出布");
                break;
            default:
                System.out.println("傻逼是吧");
                break;
        }
        input.close();
        return choice;

    }

}