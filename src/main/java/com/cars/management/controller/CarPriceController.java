package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.CarPriceDAO;
import com.cars.management.model.Car;
import com.cars.management.model.CarPrice;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarPriceService;
import com.cars.management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Controller
@RequestMapping("/carprices")
public class CarPriceController {
    @Autowired
    CarPriceService carPriceService;
    @Autowired
    CarPriceDAO carPriceDAO;
    @Autowired
    CarDAO carDAO;
    @RequestMapping(value =  "")
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getCarPrices(page));
        return "carprices";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("carlist", carDAO.selectCars(0,99));
        return "createcarprice";
    }

    @RequestMapping(path = "/update/", method = RequestMethod.POST)
    public String update(CarPrice carPrice){
        carPriceService.update(carPrice);
        return "redirect:/carprices/";
    }
    @RequestMapping(path = {"/{singleId}"})
    public String single(Model model, @PathVariable("singleId") int id){
        model.addAttribute("carprice", carPriceDAO.selectCarPrice(id));
        return "carprice";
    }

    private List<ViewObject> getCarPrices(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<CarPrice> carPrices = carPriceService.selectCarPricesByPage(page);
        for (CarPrice carPrice : carPrices) {
            ViewObject vo = new ViewObject();
            vo.set("carprice", carPrice);
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
