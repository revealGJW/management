package com.cars.management.controller;

import com.cars.management.dao.SellerDAO;
import com.cars.management.model.Seller;
import com.cars.management.model.ViewObject;
import com.cars.management.service.OrderService;
import com.cars.management.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @RequestMapping("/create")
    public String create(){
        return "createseller";
    }
    @RequestMapping(path = "/update/", method = RequestMethod.POST)
    public String update(Seller seller){
        //@RequestParam("name") String name, @RequestParam("age") int age,@RequestParam("tel") String tel
        /*Seller seller = new Seller();
        seller.setName(name);
        seller.setAge(age);
        seller.setTel(tel);*/
        sellerDAO.addSeller(seller);
        return "redirect:/sellers/" + seller.getId();
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
