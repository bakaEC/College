package com.cn.kd;

public class Dog extends Pet{
    private String strain;

    public Dog(String name,String strain){
        super(name);
        this.strain=strain;
    }
    public void print(){
        super.print();
        System.out.println("品种"+strain);
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }
}