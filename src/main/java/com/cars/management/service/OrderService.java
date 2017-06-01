package com.cars.management.service;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.OrderDAO;
import com.cars.management.dao.SellerDAO;
import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import com.cars.management.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

    public List<Order> selectOrdersByStatus(String status, int sellerId, int page) {
        return orderDAO.selectOrdersByStatus(status, sellerId,(page - 1) * 10, 10);
    }

    public float selectTotalBySellerId(int id){
        float ans = 0;
        try {
            ans = orderDAO.getTotalById(id);
        }catch (Exception e){
            return 0;
        }
        return ans;
    }
    public float complated(){
        return orderDAO.complated();
    }

    public float getPending(){
        return orderDAO.getPending();
    }

    public void updateOrder(Order order){
        CarPrice carPrice = carPriceService.selectCarPricesNow(order.getCarId());
        order.setTotal(carPrice.getPrice() * carPrice.getDiscount() * order.getNum());
        if(order.getStatus().equals("已交货"))
            order.setFinishTime(new Date());
        orderDAO.update(order);

    }

    public int getNum(){
        return orderDAO.getNum();
    }
    public void addOrder(Order order){
        order.setCreateTime(new Date());
        CarPrice carPrice = carPriceService.selectCarPricesNow(order.getCarId());
        order.setTotal(carPrice.getPrice() * carPrice.getDiscount() * order.getNum());
        if(order.getStatus().equals("已交货"))
            order.setFinishTime(new Date());
        orderDAO.addOrder(order);

    }
}
