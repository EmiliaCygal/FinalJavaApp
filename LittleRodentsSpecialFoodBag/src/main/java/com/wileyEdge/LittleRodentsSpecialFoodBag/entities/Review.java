package com.wileyEdge.LittleRodentsSpecialFoodBag.entities;

import java.util.Objects;

public class Review {

    private int id;
    private int orderId;
    private int deliveryTimeScore;
    private int priceScore;
    private int tastinessScore;
    private int bundleScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryTimeScore() {
        return deliveryTimeScore;
    }

    public void setDeliveryTimeScore(int deliveryTimeScore) {
        this.deliveryTimeScore = deliveryTimeScore;
    }

    public int getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(int priceScore) {
        this.priceScore = priceScore;
    }

    public int getTastinessScore() {
        return tastinessScore;
    }

    public void setTastinessScore(int tastinessScore) {
        this.tastinessScore = tastinessScore;
    }

    public int getBundleScore() {
        return bundleScore;
    }

    public void setBundleScore(int bundleScore) {
        this.bundleScore = bundleScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && orderId == review.orderId && deliveryTimeScore == review.deliveryTimeScore && priceScore == review.priceScore && tastinessScore == review.tastinessScore && bundleScore == review.bundleScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, deliveryTimeScore, priceScore, tastinessScore, bundleScore);
    }
}
