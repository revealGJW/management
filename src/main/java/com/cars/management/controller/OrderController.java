package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.OrderDAO;
import com.cars.management.model.Car;
import com.cars.management.model.Order;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarService;
import com.cars.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    @RequestMapping(value =  "")
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getCars(page));
        return "orders";
    }


    @RequestMapping(path = {"/{singleId}"})
    public String single(Model model, @PathVariable("singleId") int id){
        model.addAttribute("order", orderDAO.selectOrder(id));
        return "order";
    }

    private List<ViewObject> getCars(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Order> orders = orderService.selectOrdersByPage(page);
        for (Order order : orders) {
            ViewObject vo = new ViewObject();
            vo.set("order", order);
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
