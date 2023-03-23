package com.wileyEdge.LittleRodentsSpecialFoodBag.dao;

import com.wileyEdge.LittleRodentsSpecialFoodBag.entities.Order;
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
public class OrderDaoDB implements OrderDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Order getOrderById(int id) {
        try {
            final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
            return jdbc.queryForObject(GET_ORDER_BY_ID, new OrderMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrder() {
        final String GET_ALL_ORDERS = "SELECT * FROM orders";
        return jdbc.query(GET_ALL_ORDERS, new OrderMapper());
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        final String INSERT_ORDER = "INSERT INTO orders(foodPlaceId, orderStatus, foodBundle) " +
                "VALUES(?,?,?)";
        jdbc.update(INSERT_ORDER,
                order.getFoodPlaceId(),
                order.getOrderStatus(),
                order.getFoodBundle());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        order.setId(newId);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        final String UPDATE_ORDER = "UPDATE orders SET foodPlaceId = ?, orderStatus = ?, " +
                "foodBundle = ? WHERE id = ?";
        jdbc.update(UPDATE_ORDER,
                order.getFoodPlaceId(),
                order.getOrderStatus(),
                order.getFoodBundle(),
                order.getId());
    }

    @Override
    @Transactional
    public void deleteOrderById(int id) {
        final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
        jdbc.update(DELETE_ORDER, id);
    }

    public static final class OrderMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int index) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setFoodPlaceId(rs.getInt("foodPlaceId"));
            order.setOrderStatus(rs.getString("orderStatus"));
            order.setFoodBundle(rs.getString("foodBundle"));

            return order;
        }
    }
}
