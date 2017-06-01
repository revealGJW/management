package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.OrderDAO;
import com.cars.management.dao.SellerDAO;
import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import com.cars.management.model.Order;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarService;
import com.cars.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    CarDAO carDAO;
    @Autowired
    SellerDAO sellerDAO;
    @RequestMapping(value =  "")
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getOrders(page));
        return "orders";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("carlist", carDAO.selectCars(0,99));
        model.addAttribute("sellerlist", sellerDAO.selectSellers(0,99));
        return "createorder";
    }

    @RequestMapping(path = "/add/", method = RequestMethod.POST)
    public String add(Order order){
        orderService.addOrder(order);
        return "redirect:/orders/";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("order", orderDAO.selectOrder(id));
        model.addAttribute("car", carDAO.selectCar(orderDAO.selectOrder(id).getCarId()));
        model.addAttribute("seller", sellerDAO.selectSeller(orderDAO.selectOrder(id).getSellerId()));
        model.addAttribute("carlist", carDAO.selectCars(0,99));
        model.addAttribute("sellerlist", sellerDAO.selectSellers(0,99));
        return "updateorder";
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST)
    public String update(Order order){
        orderService.updateOrder(order);
        return "redirect:/orders/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        orderDAO.delete(id);
        return "redirect:/orders/";
    }

    @RequestMapping("/waitpay")
    public String wait(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        String shit = "待支付";
        model.addAttribute("vos",getOrdersByStatus(shit, page));
        return "orders";
    }

    @RequestMapping("/alreadypay")
    public String already(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        String shit = "已支付";
        model.addAttribute("vos",getOrdersByStatus(shit, page));
        return "orders";
    }

    @RequestMapping("/finish")
    public String finish(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        String shit = "已交货";
        model.addAttribute("vos",getOrdersByStatus(shit, page));
        return "orders";
    }
    @RequestMapping(path = {"/{singleId}"})
    public String single(Model model, @PathVariable("singleId") int id){
        model.addAttribute("order", orderDAO.selectOrder(id));
        return "order";
    }

    private List<ViewObject> getOrders(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Order> orders = orderService.selectOrdersByPage(page);
        for (Order order : orders) {
            ViewObject vo = new ViewObject();
            vo.set("order", order);
            vo.set("car", carDAO.selectCar(order.getCarId()));
            vo.set("seller", sellerDAO.selectSeller(order.getSellerId()));
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    private List<ViewObject> getOrdersByStatus(String status, int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Order> orders = orderService.selectOrdersByStatus(status, 0, page);
        for (Order order : orders) {
            ViewObject vo = new ViewObject();
            vo.set("order", order);
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
