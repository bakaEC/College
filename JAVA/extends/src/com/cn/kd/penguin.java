package com.cn.kd;

public class penguin extends Pet{

    private String Sex;
    public penguin(String name, String sex){
        super(name);
        this.Sex=sex;
    }
    public void print(){
        super.print();
        System.out.println("宠物的性别："+Sex);
    }
}