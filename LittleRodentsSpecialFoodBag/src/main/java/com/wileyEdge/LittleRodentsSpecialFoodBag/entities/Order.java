package com.wileyEdge.LittleRodentsSpecialFoodBag.entities;

import java.util.Objects;

public class Order {

    private int id;

    private int foodPlaceId;
    private String orderStatus;

    private String foodBundle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodPlaceId() {
        return foodPlaceId;
    }

    public void setFoodPlaceId(int foodPlaceId) {
        this.foodPlaceId = foodPlaceId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFoodBundle() {
        return foodBundle;
    }

    public void setFoodBundle(String foodBundle) {
        this.foodBundle = foodBundle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && foodPlaceId == order.foodPlaceId && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(foodBundle, order.foodBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foodPlaceId, orderStatus, foodBundle);
    }
}
