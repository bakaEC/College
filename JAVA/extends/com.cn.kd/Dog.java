package com.cn.kd;

public class Dog{
    private String strain;

    public Dog(String name,String strain){
        super(name);
        this.strain=strain;
    }
    public void print(){
        super.print();
        System.out.println("品种"+Strain);
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }
}