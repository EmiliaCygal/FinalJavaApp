package com.wileyEdge.LittleRodentsSpecialFoodBag;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FoodPlaceDaoDBTest {

    @Autowired
    FoodPlaceDao foodPlaceDao;

    public FoodPlaceDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        for (FoodPlace order : foodPlaces) {
            foodPlaceDao.deleteFoodPlaceById(order.getId());
        }

    }

    @AfterEach
    public void tearDown() {
        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();
        for (FoodPlace order : foodPlaces) {
            foodPlaceDao.deleteFoodPlaceById(order.getId());
        }
    }

    @Test
    public void testAddAndGetFoodPlace() {
        FoodPlace foodPlace = new FoodPlace();
        foodPlace.setName("Vaggos");
        foodPlace.setCategory("Grill");
        foodPlaceDao.addFoodPlace(foodPlace);

        FoodPlace fromDao = foodPlaceDao.getFoodPlaceById(foodPlace.getId());

        assertEquals(foodPlace, fromDao);
    }

    @Test
    public void testGetAllFoodPlaces() {
        FoodPlace foodPlace1 = new FoodPlace();
        foodPlace1.setName("Vaggos");
        foodPlace1.setCategory("Grill");
        foodPlaceDao.addFoodPlace(foodPlace1);

        FoodPlace foodPlace2 = new FoodPlace();
        foodPlace2.setName("Roma Pizza");
        foodPlace2.setCategory("Italian");
        foodPlaceDao.addFoodPlace(foodPlace2);

        List<FoodPlace> foodPlaces = foodPlaceDao.getAllFoodPlace();

        assertEquals(2, foodPlaces.size());
        assertTrue(foodPlaces.contains(foodPlace1));
        assertTrue(foodPlaces.contains(foodPlace2));
    }

    @Test
    public void testUpdateFoodPlace() {
        FoodPlace foodPlace = new FoodPlace();
        foodPlace.setName("Vaggos");
        foodPlace.setCategory("Grill");
        foodPlaceDao.addFoodPlace(foodPlace);

        FoodPlace fromDao = foodPlaceDao.getFoodPlaceById(foodPlace.getId());
        assertEquals(foodPlace, fromDao);

        foodPlace.setName("Grill Brothers");
        foodPlaceDao.updateFoodPlace(foodPlace);

        assertNotEquals(foodPlace, fromDao);

        fromDao = foodPlaceDao.getFoodPlaceById(foodPlace.getId());

        assertEquals(foodPlace, fromDao);
    }

    @Test
    public void testDeleteFoodPlaceById() {
        FoodPlace foodPlace = new FoodPlace();
        foodPlace.setName("Vaggos");
        foodPlace.setCategory("Grill");
        foodPlaceDao.addFoodPlace(foodPlace);

        FoodPlace fromDao = foodPlaceDao.getFoodPlaceById(foodPlace.getId());
        assertEquals(foodPlace, fromDao);

        foodPlaceDao.deleteFoodPlaceById(foodPlace.getId());

        fromDao = foodPlaceDao.getFoodPlaceById(foodPlace.getId());
        assertNull(fromDao);
    }
}


