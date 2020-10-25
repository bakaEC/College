package com.uestc.cars;

public abstract class MotoVehicle {
    private  String no;
    private  String brand;

    public  MotoVehicle(){

    }
    public MotoVehicle(String no,String brand){
        this.no=no;
        this.brand=brand;
    }
    public abstract int carRent(int Days);

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
