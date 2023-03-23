package com.wileyEdge.LittleRodentsSpecialFoodBag.entities;

import java.util.Objects;

public class FoodPlace {

    private int id;
    private String name;
    private String category;
    private double deliveryTimeScore;
    private double priceScore;
    private double tastinessScore;
    private double overallScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDeliveryTimeScore() {
        return deliveryTimeScore;
    }

    public void setDeliveryTimeScore(double deliveryTimeScore) {
        this.deliveryTimeScore = deliveryTimeScore;
    }

    public double getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(double priceScore) {
        this.priceScore = priceScore;
    }

    public double getTastinessScore() {
        return tastinessScore;
    }

    public void setTastinessScore(double tastinessScore) {
        this.tastinessScore = tastinessScore;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodPlace foodPlace = (FoodPlace) o;
        return id == foodPlace.id && deliveryTimeScore == foodPlace.deliveryTimeScore && priceScore == foodPlace.priceScore && tastinessScore == foodPlace.tastinessScore && overallScore == foodPlace.overallScore && Objects.equals(name, foodPlace.name) && Objects.equals(category, foodPlace.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, deliveryTimeScore, priceScore, tastinessScore, overallScore);
    }
}
