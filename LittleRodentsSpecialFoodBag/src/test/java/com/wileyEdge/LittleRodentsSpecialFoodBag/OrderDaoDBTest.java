package com.wileyEdge.LittleRodentsSpecialFoodBag;

import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.FoodPlaceDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.dao.OrderDao;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.FoodPlace;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDaoDBTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    FoodPlaceDao foodPlaceDao;

    public OrderDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

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

    }

    @AfterEach
    public void tearDown() {
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
    public void testAddAndGetOrder() {
        Order order = new Order();
        order.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(0).getId());
        order.setFoodBundle("Small");
        order.setOrderStatus("Delivered");
        order = orderDao.addOrder(order);

        Order fromDao = orderDao.getOrderById(order.getId());

        assertEquals(order, fromDao);
    }

    @Test
    public void testGetAllOrders() {
        Order order = new Order();
        order.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(0).getId());
        order.setFoodBundle("Small");
        order.setOrderStatus("Delivered");
        order = orderDao.addOrder(order);

        Order order2 = new Order();
        order2.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(1).getId());
        order2.setFoodBundle("Large");
        order2.setOrderStatus("Delivered");
        order2 = orderDao.addOrder(order2);

        List<Order> orders = orderDao.getAllOrder();

        assertEquals(2, orders.size());
        assertTrue(orders.contains(order));
        assertTrue(orders.contains(order2));
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(0).getId());
        order.setFoodBundle("Small");
        order.setOrderStatus("Delivered");
        order = orderDao.addOrder(order);

        Order fromDao = orderDao.getOrderById(order.getId());
        assertEquals(order, fromDao);

        order.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(1).getId());
        orderDao.updateOrder(order);

        assertNotEquals(order, fromDao);

        fromDao = orderDao.getOrderById(order.getId());

        assertEquals(order, fromDao);
    }

    @Test
    public void testDeleteOrderById() {
        Order order = new Order();
        order.setFoodPlaceId(foodPlaceDao.getAllFoodPlace().get(0).getId());
        order.setFoodBundle("Small");
        order.setOrderStatus("Delivered");
        order = orderDao.addOrder(order);

        Order fromDao = orderDao.getOrderById(order.getId());
        assertEquals(order, fromDao);

        orderDao.deleteOrderById(order.getId());

        fromDao = orderDao.getOrderById(order.getId());
        assertNull(fromDao);
    }
}


