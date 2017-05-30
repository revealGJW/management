package com.cars.management.model;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by GJW on 2017/5/29.
 */

public class Car {
    private int id;
    private String brand;
    private String name;
    private int seatnum;
    private String gearbox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatnum() {
        return seatnum;
    }

    public void setSeatnum(int seatnum) {
        this.seatnum = seatnum;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }
}
