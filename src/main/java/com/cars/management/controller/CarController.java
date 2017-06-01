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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJW on 2017/5/29.
 */
@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;
    @Autowired
    CarDAO carDAO;
    @Autowired
    CarPriceService carPriceService;
    @Autowired
    CarPriceDAO carPriceDAO;
    @RequestMapping(value =  "")
    public String index(Model model, @RequestParam(value = "page",required = false, defaultValue = "1") int page){

        model.addAttribute("vos", getCars(page));
        model.addAttribute("page",page);
        return "cars";
    }

    @RequestMapping(value = "/{carId}")
    public String carDetail(Model model, @PathVariable("carId") int carId, @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        model.addAttribute("car", carDAO.selectCar(carId));
        model.addAttribute("carprices", carPriceService.selectCarPricesByCarId(carId, page));
        return "cardetail";
    }

    @RequestMapping("/create")
    public String create(Car car){
        return "createcar";
    }

    @RequestMapping(path = "/add/", method = RequestMethod.POST)
    public String add(Car car){
        carDAO.addCar(car);
        return "redirect:/cars/" + car.getId();
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("car", carDAO.selectCar(id));
        return "updatecar";
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST)
    public String update(Car car){
        carDAO.update(car);
        return "redirect:/cars/" + car.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        carDAO.delete(id);
        return "redirect:/cars/";
    }

    private List<ViewObject> getCars(int page){
        List<ViewObject> vos = new ArrayList<ViewObject>();
        List<Car> cars = carService.selectCarsByPage(page);
        for (Car car : cars) {
            ViewObject vo = new ViewObject();
            vo.set("car", car);
            // vo.set("user", userService.getUser(question.getUserId()));
            vo.set("carprice", carPriceService.selectCarPricesNow(car.getId()));
            vos.add(vo);
        }
        return vos;
    }
}
