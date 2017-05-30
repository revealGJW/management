package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.dao.CarPriceDAO;
import com.cars.management.model.Car;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarPriceService;
import com.cars.management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Controller
public class CarController {

    @Autowired
    CarService carService;
    @Autowired
    CarDAO carDAO;
    @Autowired
    CarPriceService carPriceService;
    @Autowired
    CarPriceDAO carPriceDAO;
    @RequestMapping(value =  "/cars")
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getCars(page));
        return "cars";
    }

    @RequestMapping(value = "/cars/{carId}")
    public String carDetail(Model model, @PathVariable("carId") int carId, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        model.addAttribute("car", carDAO.selectCar(carId));
        model.addAttribute("carprices", carPriceService.selectCarPricesByCarId(carId, page));
        return "cardetail";
    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(){
        return "success";
    }



    private List<ViewObject> getCars(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Car> cars = carService.selectCarsByPage(page);
        for (Car car : cars) {
            ViewObject vo = new ViewObject();
            vo.set("car", car);
            // vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
