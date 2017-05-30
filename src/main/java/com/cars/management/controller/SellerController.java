package com.cars.management.controller;

import com.cars.management.dao.SellerDAO;
import com.cars.management.model.Seller;
import com.cars.management.model.ViewObject;
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
@RequestMapping("/sellers")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @Autowired
    SellerDAO sellerDAO;
    @Autowired
    OrderService orderService;
    @RequestMapping(path = {""})
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getSellers(page));
        return "sellers";
    }


    @RequestMapping(path = {"/{singleId}"})
    public String single(Model model, @PathVariable("singleId") int id, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        model.addAttribute("seller", sellerDAO.selectSeller(id));
        model.addAttribute("orders",orderService.selectOrdersBySeller(id, page));
        return "seller";
    }

    private List<ViewObject> getSellers(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Seller> sellers = sellerService.selectSellersByPage(page);
        for (Seller seller  : sellers) {
            ViewObject vo = new ViewObject();
            vo.set("seller", seller);
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
