package com.cars.management.controller;

import com.cars.management.dao.CarDAO;
import com.cars.management.model.Car;
import com.cars.management.model.ViewObject;
import com.cars.management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfy on 2017/5/29.
 */
@Controller
public class indexController {

    @Autowired
    CarService carService;
    @Autowired
    CarDAO carDAO;
    @RequestMapping(path = {"/","/index"})
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getCars(page));
        return "index";
    }


    @RequestMapping(path = {"/{singleId}"})
    public String single(Model model, @PathVariable("singleId") int id){
        model.addAttribute("car", carDAO.selectCar(id));
        return "car";
    }

    @RequestMapping("/createseller")
    public String createseller(){
        return "createseller";
    }

    @RequestMapping("/createcar")
    public String createcar(){
        return "createcar";
    }
    @RequestMapping("/createorder")
    public String createorder(){
        return "createorder";
    }
    @RequestMapping("/createcarprice")
    public String createcarprice(){
        return "createcarprice";
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
