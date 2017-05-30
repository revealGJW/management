package com.cars.management.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by GJW on 2017/5/29.
 */
public class CarPrice {
    private int id;
    private int carId;
    private float price;
    private float discount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date changeDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
