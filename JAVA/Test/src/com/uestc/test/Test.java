package com.uestc.test;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Dog dog = new Dog("小白","拉布拉多");
        System.out.println("请输入宠物名字");
        dog.setName(input.next());
        dog.setHealth(100);
        dog.setLove(20);
        dog.print();
    }
}
