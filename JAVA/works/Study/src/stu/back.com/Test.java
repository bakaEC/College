package stu;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Account act = new Account();
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("1.存款 2.取款 0.退出：");
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("请输入存款金额：");
                double saveMoney = input.nextDouble();
                act.save(saveMoney);

            } else if (choice == 2) {
                System.out.println("请输入取款金额:");
                double depMoney = input.nextDouble();
                act.deposit(depMoney);
            } else if (choice == 0) {
                flag = false;
                System.out.println("感谢使用");
                input.close();
            } else {
                System.out.println("输入有误");
            }
        }
    }

}