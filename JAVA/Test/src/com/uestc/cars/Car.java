package com.uestc.cars;

public class Car extends MotoVehicle {
    private String type;
    public Car(){

    }
    public Car(String no,String brand,String type){
        super(no,brand);
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int carRent(int Days) {
        if ("1".equals(type)){
            return Days*500;
        }else if("2".equals(type)){
            return Days*600;
        }
        return 0;
    }
}
