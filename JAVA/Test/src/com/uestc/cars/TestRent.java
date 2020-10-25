package com.uestc.cars;

import java.util.Scanner;

public class TestRent {
    public static void main(String[] args) {
        String no,brand,mtype,type;
        int seatCount,days,rent;

        Car car;
        Bus bus;

        Scanner in = new Scanner(System.in);
        System.out.println("欢迎进入汽车系统");
        System.out.println("请输入要租的天数");
        days = in.nextInt();
        System.out.println("请输入要租的汽车类型（1.轿车 2.客车）");
        mtype=in.next();
        if("1".equals(mtype)){
            System.out.println("请输入汽车品牌（1.宝马 2.别克）");
            brand = in.next();
            System.out.println("请输入汽车的型号");
            if("1".equals(brand)){
                System.out.println("1. 550i");
            }else {
                System.out.println("2.GL8");
            }
            type=in.next();
            no="川A Y12345";
            car = new Car(no,brand,type);
            rent = car.carRent(days);
        }else {
            System.out.println("请输入要租的客车品牌（1. 金杯 2.青龙）");
            brand=in.next();
            System.out.println("请输入客车座位数量");
            seatCount=in.nextInt();
            no="川A 234561";
            bus=new Bus(no,brand,seatCount);
            rent=bus.carRent(days);
        }
        System.out.println("你好，你要支付的费用为"+rent+"元");
    }
}
