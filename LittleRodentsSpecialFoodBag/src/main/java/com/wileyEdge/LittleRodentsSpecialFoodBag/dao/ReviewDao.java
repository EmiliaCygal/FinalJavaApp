package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Review;

import java.util.List;

public interface ReviewDao {

    Review getReviewById(int id);
    List<Review> getAllReviews();

    List<Review> getAllFoodPlaceReviewsAverage(int foodPlaceId);

    Review addReview(Review review);
    void updateReview(Review review);
    void deleteReviewById(int id);

}

