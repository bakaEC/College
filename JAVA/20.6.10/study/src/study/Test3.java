package study;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        stringCounter st = new stringCounter();
        System.out.println("请输入您的字符串:");
        String string = input.nextLine();

        System.out.println("请输入要统计的字符:");
        String find = input.nextLine();

        System.out.println("字符串中共有" + st.count(string, find) + "个" + find);
    }
}