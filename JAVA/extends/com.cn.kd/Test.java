package com.cn.kd;

public class Test{
    public static void main(String[] args) {
        Pet pet = new pet(小贝);
        pet.setHealth(50);
        pet.setLove(80);
        pet.print();

        Dog g = new Dog("小白","拉布拉多"){
            g.setHealth(50);
            g.setLove(80);
            g.print();
        }

        Penguin p = new Penguin("小Q","Q仔"){
            p.setHealth(50);
            p.setLove(80);
            p.print();
        }
    }
}
