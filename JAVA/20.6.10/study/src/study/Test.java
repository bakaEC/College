package study;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Register reg = new Register();
        String uname, p1, p2;
        System.out.println("*************欢迎进入注册系统！**************");
        boolean resp = false;
        do {
            System.out.println("请输入用户名");
            uname = input.next();
            System.out.println("请输入密码");
            p1 = input.next();
            System.out.println("请再次输入密码");
            p2 = input.next();
            resp = reg.verify(uname, p1, p2);
        } while (!resp);

    }

}