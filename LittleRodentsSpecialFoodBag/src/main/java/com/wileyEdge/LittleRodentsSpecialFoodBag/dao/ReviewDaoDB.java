package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewDaoDB implements ReviewDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Review getReviewById(int id) {
        try {
            final String GET_REVIEW_BY_ID = "SELECT * FROM reviews WHERE id = ?";
            return jdbc.queryForObject(GET_REVIEW_BY_ID, new ReviewMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Review> getAllReviews() {
        final String GET_ALL_REVIEWS = "SELECT * FROM reviews";
        return jdbc.query(GET_ALL_REVIEWS, new ReviewMapper());
    }

    @Override
    public List<Review> getAllFoodPlaceReviewsAverage(int foodPlaceId) {
        final String GET_ALL_FOODPLACE_REVIEWS = "SELECT * FROM reviews r LEFT JOIN orders o ON r.orderId = o.id WHERE o.foodPlaceId = ? ";
        return jdbc.query(GET_ALL_FOODPLACE_REVIEWS, new ReviewMapper(),foodPlaceId);
    }

    @Override
    @Transactional
    public Review addReview(Review review) {
        final String INSERT_REVIEW = "INSERT INTO reviews (orderId, deliveryTimeScore, priceScore, tastinessScore, bundleScore) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_REVIEW,
                review.getOrderId(),
                review.getDeliveryTimeScore(),
                review.getPriceScore(),
                review.getTastinessScore(),
                review.getBundleScore());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        review.setId(newId);
        return review;
    }

    @Override
    public void updateReview(Review review) {
        final String UPDATE_ORDER = "UPDATE reviews SET orderId = ?, deliveryTimeScore = ?, " +
                "priceScore = ?, tastinessScore = ?, bundleScore = ? WHERE id = ?";
        jdbc.update(UPDATE_ORDER,
                review.getOrderId(),
                review.getDeliveryTimeScore(),
                review.getPriceScore(),
                review.getTastinessScore(),
                review.getBundleScore(),
                review.getId());
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {
        final String DELETE_ORDER = "DELETE FROM reviews WHERE id = ?";
        jdbc.update(DELETE_ORDER, id);
    }

    public static final class ReviewMapper implements RowMapper<Review> {

        @Override
        public Review mapRow(ResultSet rs, int index) throws SQLException {
            Review review = new Review();
            review.setId(rs.getInt("id"));
            review.setOrderId(rs.getInt("orderId"));
            review.setDeliveryTimeScore(rs.getInt("deliveryTimeScore"));
            review.setPriceScore(rs.getInt("priceScore"));
            review.setTastinessScore(rs.getInt("tastinessScore"));
            review.setBundleScore(rs.getInt("bundleScore"));

            return review;
        }
    }
}