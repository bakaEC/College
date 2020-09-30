package com.cn.kd;

public class Penguin extends Pet{

    private String Sex;
    public Penguin(String name,String sex){
        super(name);
        this.Sex=sex;
    }
    public void print(){
        super.print();
        System.out.println("宠物的性别："+sex);
    }
}