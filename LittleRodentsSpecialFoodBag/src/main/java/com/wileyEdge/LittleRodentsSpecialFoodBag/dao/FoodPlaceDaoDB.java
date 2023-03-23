package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FoodPlaceDaoDB implements FoodPlaceDao{

    private List<FoodPlace> selectedPlaces = new ArrayList<>();

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public FoodPlace getFoodPlaceById(int id) {
        try {
            final String GET_FOODPLACE_BY_ID = "SELECT * FROM foodPlaces WHERE id = ?";
            return jdbc.queryForObject(GET_FOODPLACE_BY_ID, new FoodPlaceMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<FoodPlace> getAllFoodPlace() {
        final String GET_ALL_FOODPLACES = "SELECT * FROM foodPlaces";
        return jdbc.query(GET_ALL_FOODPLACES, new FoodPlaceMapper());
    }

    @Override
    public List<FoodPlace> getFoodPlacesByCategory(String category) {
        final String GET_CAT_FOODPLACES = "SELECT * FROM foodPlaces WHERE category = ?";
        return jdbc.query(GET_CAT_FOODPLACES, new FoodPlaceMapper(), category);
    }

    @Override
    public List<FoodPlace> getSelectedPlaces() {
        return selectedPlaces;
    }

    @Override
    public void setSelectedPlaces(List<FoodPlace> selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }

    @Override
    public List<FoodPlace> getFoodPlaceByScores(Integer deliveryTimeScore, Integer priceScore, Integer tastinessScore, Integer overallScore){
        final String GET_FILTERED_FOODPLACES = "SELECT * FROM foodPlaces WHERE deliveryTimeScore > ? AND priceScore > ? AND tastinessScore > ? AND overallScore > ?";
        return jdbc.query(GET_FILTERED_FOODPLACES, new FoodPlaceMapper(), deliveryTimeScore, priceScore, tastinessScore, overallScore);
    }

    @Override
    @Transactional
    public FoodPlace addFoodPlace(FoodPlace foodPlace) {
        final String INSERT_FOODPLACE = "INSERT INTO foodPlaces(name, category, deliveryTimeScore, priceScore, tastinessScore, overallScore) " +
                "VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_FOODPLACE,
                foodPlace.getName(),
                foodPlace.getCategory(),
                foodPlace.getDeliveryTimeScore(),
                foodPlace.getPriceScore(),
                foodPlace.getTastinessScore(),
                foodPlace.getOverallScore());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        foodPlace.setId(newId);
        return foodPlace;
    }

    @Override
    public void updateFoodPlace(FoodPlace foodPlace) {
        final String UPDATE_FOODPLACE = "UPDATE foodPlaces SET name = ?, category = ?, " +
                "deliveryTimeScore = ?, priceScore = ?,tastinessScore = ?,overallScore = ? WHERE id = ?";
        jdbc.update(UPDATE_FOODPLACE,
                foodPlace.getName(),
                foodPlace.getCategory(),
                foodPlace.getDeliveryTimeScore(),
                foodPlace.getPriceScore(),
                foodPlace.getTastinessScore(),
                foodPlace.getOverallScore(),
                foodPlace.getId());
    }

    @Override
    @Transactional
    public void deleteFoodPlaceById(int id) {
        final String DELETE_FOODPLACE = "DELETE FROM foodPlaces WHERE id = ?";
        jdbc.update(DELETE_FOODPLACE, id);
    }

    public static final class FoodPlaceMapper implements RowMapper<FoodPlace> {

        @Override
        public FoodPlace mapRow(ResultSet rs, int index) throws SQLException {
            FoodPlace foodPlace = new FoodPlace();
            foodPlace.setId(rs.getInt("id"));
            foodPlace.setName(rs.getString("name"));
            foodPlace.setCategory(rs.getString("category"));
            foodPlace.setDeliveryTimeScore(rs.getDouble("deliveryTimeScore"));
            foodPlace.setPriceScore(rs.getDouble("priceScore"));
            foodPlace.setTastinessScore(rs.getDouble("tastinessScore"));
            foodPlace.setOverallScore(rs.getDouble("overallScore"));

            return foodPlace;
        }
    }
}