package com.wileyEdge.LittleRodentsSpecialFoodBag.service;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.OrderDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.ReviewDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Review;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final OrderDao orderDao;
    private final ReviewDao reviewDao;
    private final FoodPlaceDao foodPlaceDao;

    public Service(OrderDao orderDao, ReviewDao reviewDao, FoodPlaceDao foodPlaceDao) {
        this.orderDao = orderDao;
        this.reviewDao = reviewDao;
        this.foodPlaceDao = foodPlaceDao;
    }

    public int currentOrderId(){
        List<Order> orders = orderDao.getAllOrder();
        if (orders.size() > 0){
            return orders.get(orders.size()-1).getId();
        }else{
            return 1;
        }
    }

    public void calcFoodPlaceScores(int orderId){
        Order order = orderDao.getOrderById(orderId);
        List<Review> reviews = reviewDao.getAllFoodPlaceReviewsAverage(order.getFoodPlaceId());
        double avgDeliveryTimeScore = 0.0;
        double avgPriceScore = 0.0;
        double avgTastinessScore = 0.0;
        double avgOverallScore = 0.0;
        for (Review review: reviews){
            avgDeliveryTimeScore += review.getDeliveryTimeScore();
            avgPriceScore += review.getPriceScore();
            avgTastinessScore += review.getTastinessScore();
            avgOverallScore += review.getBundleScore();
        }
        avgDeliveryTimeScore /= reviews.size();
        avgPriceScore /= reviews.size();
        avgTastinessScore /= reviews.size();
        avgOverallScore /= reviews.size();

        FoodPlace foodPlace = foodPlaceDao.getFoodPlaceById(order.getFoodPlaceId());
        foodPlace.setDeliveryTimeScore(avgDeliveryTimeScore);
        foodPlace.setPriceScore(avgPriceScore);
        foodPlace.setTastinessScore(avgTastinessScore);
        foodPlace.setOverallScore(avgOverallScore);
        foodPlaceDao.updateFoodPlace(foodPlace);
    }

}
