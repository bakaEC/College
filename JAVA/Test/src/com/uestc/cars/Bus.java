package com.uestc.cars;

public class Bus extends MotoVehicle {

    private int seatCount;

    public Bus(){}
    public Bus(String no,String brand,int seatCount){
        super(no,brand);
        this.seatCount=seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    @Override
    public int carRent(int Days) {
        if(seatCount<50){
            return seatCount*500;
        }else
            return seatCount*400;
    }
}
