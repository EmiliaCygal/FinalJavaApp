package com.wileyEdge.LittleRodentsSpecialFoodBag.controller;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.OrderDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;
import com.wileyEdge.LittleRodentsSpecialFoodBag.service.Service;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {

    @GetMapping("index")
    public String viewIndex() {
        return "index";
    }

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private FoodPlaceDao foodPlaceDao;

    @Autowired
    private Service service;

    @GetMapping("order")
    public String displayOrders(Model model) {
        List<Order> orders = orderDao.getAllOrder();
        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        FoodPlace foodPlace = new FoodPlace();
        model.addAttribute("order", orders);
        model.addAttribute("foodPlaces", foodPlaces);
        model.addAttribute("foodPlace2", foodPlace);
        return "order";
    }

    @PostMapping("addOrder")
    public String addOrder(int foodPlaceId, String foodBundle) {
        Order order = new Order();
        order.setId(service.currentOrderId());
        order.setOrderStatus("Delivered");
        order.setFoodPlaceId(foodPlaceId);
        order.setFoodBundle(foodBundle);
        orderDao.addOrder(order);

        return "review";
    }
}
