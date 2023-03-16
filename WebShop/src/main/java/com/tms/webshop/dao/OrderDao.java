package com.tms.webshop.dao;

import com.tms.webshop.model.Order;

import java.util.List;

public interface OrderDao {
    void addOrder(int userId, Order order);

    List<Order> getOrdersByUserId(int userId);
}
