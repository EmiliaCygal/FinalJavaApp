package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;

import java.util.List;

public interface OrderDao {

    Order getOrderById(int id);
    List<Order> getAllOrder();
    Order addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrderById(int id);

}
