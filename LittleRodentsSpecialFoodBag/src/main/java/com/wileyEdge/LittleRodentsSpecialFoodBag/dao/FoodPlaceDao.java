package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;

import java.util.List;

public interface FoodPlaceDao {

    FoodPlace getFoodPlaceById(int id);
    List<FoodPlace> getAllFoodPlace();
    FoodPlace addFoodPlace(FoodPlace foodPlace);
    void updateFoodPlace(FoodPlace foodPlace);
    void deleteFoodPlaceById(int id);
    List<FoodPlace> getFoodPlacesByCategory(String category);

    List<FoodPlace> getSelectedPlaces();

    void setSelectedPlaces(List<FoodPlace> selectedPlaces);

    List<FoodPlace> getFoodPlaceByScores(Integer deliveryTimeScore, Integer priceScore, Integer tastinessScore, Integer overallScore);

}
