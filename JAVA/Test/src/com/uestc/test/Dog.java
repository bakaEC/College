package com.uestc.test;

public class Dog extends Pet {

    private String strain;

    public Dog(String name,String strain){
        super();
        this.strain=strain;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    @Override
    public void print() {
        System.out.println("宠物名:"+getName()+"\t健康度:"+getHealth()+"\t亲密度:"+getLove()+"\t品种:"+getStrain());
    }
}
