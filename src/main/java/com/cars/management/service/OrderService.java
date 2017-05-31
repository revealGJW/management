package com.cars.management.service;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.OrderDAO;
import com.cars.management.model.Car;
import com.cars.management.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    CarPriceService carPriceService;
    public List<Order> getCars(int offset, int limit) {
        return orderDAO.selectOrders(0, offset, limit);
    }

    public List<Order> selectOrdersByPage(int page) {
        return orderDAO.selectOrders(0, (page - 1) * 10, 10);
    }

    public List<Order> selectOrdersBySeller(int sellerId, int page) {
        return orderDAO.selectOrders(sellerId, (page - 1) * 10, 10);
    }

    public void addOrder(Order order){
        order.setCreateTime(new Date());
        order.setTotal(carPriceService.selectCarPricesNow(order.getCarId()).getPrice() *carPriceService.selectCarPricesNow(order.getCarId()).getDiscount() * order.getNum());
        if(order.getStatus().equals("已交货"))
            order.setFinishTime(new Date());
        orderDAO.addOrder(order);

    }
}
