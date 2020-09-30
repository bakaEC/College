package com.cn.kd;

public class Pet{
    private String name;
    private int health;
    private int love;

    public Pet(String name){
        this.name=name;
    }

    public abstract void print();

    public void print(){
        System.out.println("宠物名称"+name+"健康度"+health+"亲密度"+love);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }
}
