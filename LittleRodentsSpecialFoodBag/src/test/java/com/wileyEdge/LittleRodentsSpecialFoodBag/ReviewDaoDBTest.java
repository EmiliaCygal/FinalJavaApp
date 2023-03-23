package com.wileyEdge.LittleRodentsSpecialFoodBag;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.OrderDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.ReviewDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewDaoDBTest {

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    FoodPlaceDao foodPlaceDao;

    @BeforeEach
    public void setUp(){
        List<Review> reviews = reviewDao.getAllReviews();
        for (Review review : reviews){
            reviewDao.deleteReviewById(review.getId());
        }

        List<Order> orders = orderDao.getAllOrder();
        for (Order order : orders) {
            orderDao.deleteOrderById(order.getId());
        }

        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        for (FoodPlace order : foodPlaces) {
            foodPlaceDao.deleteFoodPlaceById(order.getId());
        }

        FoodPlace foodPlace1 = new FoodPlace();
        foodPlace1.setName("Vaggos");
        foodPlace1.setCategory("Grill");
        foodPlaceDao.addFoodPlace(foodPlace1);

        FoodPlace foodPlace2 = new FoodPlace();
        foodPlace2.setName("Roma Pizza");
        foodPlace2.setCategory("Italian");
        foodPlaceDao.addFoodPlace(foodPlace2);

        Order order1 = new Order();
        order1.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(0).getId());
        order1.setFoodBundle("Large");
        order1.setOrderStatus("Delivered");
        orderDao.addOrder(order1);

        Order order2 = new Order();
        order2.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(1).getId());
        order2.setFoodBundle("Medium");
        order2.setOrderStatus("Delivered");
        orderDao.addOrder(order2);

    }

    @AfterEach
    public void tearDown(){

        List<Review> reviews = reviewDao.getAllReviews();
        for (Review review : reviews){
            reviewDao.deleteReviewById(review.getId());
        }

        List<Order> orders = orderDao.getAllOrder();
        for (Order order : orders) {
            orderDao.deleteOrderById(order.getId());
        }

        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        for (FoodPlace order : foodPlaces) {
            foodPlaceDao.deleteFoodPlaceById(order.getId());
        }
    }

    @Test
    public  void testAddAndGetReview(){
        Review review = new Review();
        review.setOrderId(orderDao.getAllOrder().get(0).getId());
        review.setDeliveryTimeScore(5);
        review.setPriceScore(5);
        review.setBundleScore(5);
        review = reviewDao.addReview(review);

        Review fromDao = reviewDao.getReviewById(review.getId());
        assertEquals(review, fromDao);
    }

    @Test
    public void testGetAllReviews() {
        Review review = new Review();
        review.setOrderId(orderDao.getAllOrder().get(0).getId());
        review.setDeliveryTimeScore(5);
        review.setPriceScore(5);
        review.setBundleScore(5);
        review = reviewDao.addReview(review);

        Review review2 = new Review();
        review2.setOrderId(orderDao.getAllOrder().get(1).getId());
        review2.setDeliveryTimeScore(1);
        review2.setPriceScore(1);
        review2.setBundleScore(1);
        review2 = reviewDao.addReview(review2);

        List<Review> reviews = reviewDao.getAllReviews();

        assertEquals(2, reviews.size());
        assertTrue(reviews.contains((review)));
        assertTrue(reviews.contains((review2)));
    }

    @Test
    public void testUpdateReview() {
        Review review = new Review();
        review.setOrderId(orderDao.getAllOrder().get(0).getId());
        review.setDeliveryTimeScore(5);
        review.setPriceScore(5);
        review.setBundleScore(5);
        review = reviewDao.addReview(review);

        Review fromDao = reviewDao.getReviewById(review.getId());
        assertEquals(review, fromDao);

        review.setOrderId(orderDao.getAllOrder().get(1).getId());
        reviewDao.updateReview(review);

        assertNotEquals(review, fromDao);

        fromDao = reviewDao.getReviewById(review.getId());

        assertEquals(review, fromDao);
    }

    @Test
    public void testDeleteReviewById(){
        Review review = new Review();
        review.setOrderId(orderDao.getAllOrder().get(0).getId());
        review.setDeliveryTimeScore(5);
        review.setPriceScore(5);
        review.setBundleScore(5);
        review = reviewDao.addReview(review);

        Review fromDao = reviewDao.getReviewById(review.getId());
        assertEquals(review, fromDao);

        reviewDao.deleteReviewById(review.getId());

        fromDao = reviewDao.getReviewById(review.getId());
        assertNull(fromDao);
    }

}