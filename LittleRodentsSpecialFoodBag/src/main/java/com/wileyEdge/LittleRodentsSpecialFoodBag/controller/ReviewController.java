package com.wileyEdge.LittleRodentsSpecialFoodBag.controller;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.ReviewDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Review;
import com.wileyEdge.LittleRodentsSpecialFoodBag.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private Service service;


    @GetMapping("review")
    public String displayReviews(Model model) {
        List<Review> reviews = reviewDao.getAllReviews();
        model.addAttribute("review", reviews);
        return "review";
    }

    @PostMapping("addReview")
    public String addReview(int deliveryTimeScore, int priceScore, int tastinessScore, int bundleScore) {
        Review review = new Review();
        review.setOrderId(service.currentOrderId());
        review.setDeliveryTimeScore(deliveryTimeScore);
        review.setPriceScore(priceScore);
        review.setTastinessScore(tastinessScore);
        review.setBundleScore(bundleScore);
        reviewDao.addReview(review);
        service.calcFoodPlaceScores(service.currentOrderId());
        return "thankYou";
    }

}
