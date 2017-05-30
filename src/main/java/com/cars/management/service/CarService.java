package com.cars.management.service;

import com.cars.management.dao.CarDAO;
import com.cars.management.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Service
public class CarService {
    @Autowired
    CarDAO carDAO;

    public List<Car> getCars(int offset, int limit) {
        return carDAO.selectCars(offset, limit);
    }

    public List<Car> selectCarsByPage(int page) {
        return carDAO.selectCars((page - 1) * 10, 10);
    }
}
