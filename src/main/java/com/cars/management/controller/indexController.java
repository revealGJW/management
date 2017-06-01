package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.model.Car;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarService;
import com.cars.management.service.OrderService;
import com.cars.management.service.SellerService;
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
public class indexController {
    @Autowired
    OrderService orderService;
    @Autowired
    SellerService sellerService;
    @RequestMapping(path = {"/","/index"})
    public String index(Model model){
        model.addAttribute("complated", orderService.complated());
        model.addAttribute("pending", orderService.getPending());
        model.addAttribute("ordernum", orderService.getNum());
        model.addAttribute("sellernum",sellerService.getNum());
        model.addAttribute("orders",orderService.selectOrdersByPage(1));
        model.addAttribute("sellers", sellerService.selectSellersByPage(1));
        return "index";
    }


}
