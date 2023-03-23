package com.wileyEdge.LittleRodentsSpecialFoodBag.controller;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FoodPlaceController {

    @Autowired
    private FoodPlaceDao foodPlaceDao;

    @GetMapping("allFoodPlaces")
    public String displayAllFoodPlaces(Model model) {
        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        model.addAttribute("foodPlaces", foodPlaces);
        return "allFoodPlaces";
    }

    @GetMapping("foodPlacesCuisine")
    public String displayFoodPlacesCuisine(Model model) {
        List<FoodPlace> foodPlaces = foodPlaceDao.getSelectedPlaces();
        model.addAttribute("foodPlaces", foodPlaces);
        return "foodPlacesCuisine";
    }

    @PostMapping("foodPlacesCuisine")
    public String foodPlacesCuisine(String category){
        foodPlaceDao.setSelectedPlaces(foodPlaceDao.getFoodPlacesByCategory(category));
        return "redirect:foodPlacesCuisine";
    }

    @GetMapping("foodPlacesScores")
    public String displayFoodPlacesScores(Model model) {
        List<FoodPlace> foodPlaces = foodPlaceDao.getSelectedPlaces();
        model.addAttribute("foodPlaces", foodPlaces);
        return "foodPlacesScores";
    }

    @PostMapping("foodPlacesScores")
    public String foodPlacesScores(Integer deliveryTimeScore, Integer priceScore, Integer tastinessScore, Integer overallScore){
        foodPlaceDao.setSelectedPlaces(foodPlaceDao.getFoodPlaceByScores(deliveryTimeScore, priceScore, tastinessScore, overallScore));
        return "redirect:foodPlacesScores";
    }


    @GetMapping("admin")
    public String displayFoodPlaces(Model model) {
        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        model.addAttribute("foodPlaces", foodPlaces);
        return "admin";
    }

    @PostMapping("addFoodPlace")
    public String addFoodPlace(String name, String category) {
        FoodPlace foodPlace = new FoodPlace();
        foodPlace.setName(name);
        foodPlace.setCategory(category);
        foodPlaceDao.addFoodPlace(foodPlace);

        return "redirect:/admin";
    }

    @GetMapping("deleteFoodPlace")
    public String deleteFoodPlace(Integer id) {
        foodPlaceDao.deleteFoodPlaceById(id);
        return "redirect:/admin";
    }

    @GetMapping("editFoodPlace")
    public String editFoodPlace(Integer id, Model model) {
        FoodPlace foodPlace = foodPlaceDao.getFoodPlaceById(id);
        model.addAttribute("foodPlace", foodPlace);
        return "editFoodPlace";
    }

    @PostMapping("editFoodPlace")
    public String performEditStudent(FoodPlace foodPlace) {
        foodPlaceDao.updateFoodPlace(foodPlace);
        return "redirect:/admin";
    }


}
