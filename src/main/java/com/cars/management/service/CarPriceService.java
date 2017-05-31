package com.cars.management.service;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.CarPriceDAO;
import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Service
public class CarPriceService {
    @Autowired
    CarPriceDAO carPriceDAO;

    public List<CarPrice> getCarPrices(int offset, int limit) {
        return carPriceDAO.selectCarPrices(0,offset, limit);
    }

    public List<CarPrice> selectCarPricesByPage(int page) {
        return carPriceDAO.selectCarPrices(0,(page - 1) * 10, 10);
    }

    public List<CarPrice> selectCarPricesByCarId(int carId, int page) {
        return carPriceDAO.selectCarPrices(carId,(page - 1) * 10, 10);
    }
    public String createCarPrice(Model model){
        return "createcarprice";
    }

    public void add(CarPrice carPrice){
        carPrice.setChangeDate(new Date());
        int carId = carPrice.getCarId();
        carPriceDAO.ChangeNowPriceToHistory(carId);
        carPriceDAO.addCarPrice(carPrice);
    }
    public CarPrice selectCarPricesNow(int carId) {
        return carPriceDAO.selectLatestPrice(carId);
    }
}
