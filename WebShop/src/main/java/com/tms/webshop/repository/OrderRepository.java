package com.tms.webshop.repository;

import com.tms.webshop.model.Order;

import java.util.List;

public interface OrderRepository {
    void addOrder(int userId, Order order);

    List<Order> getOrdersByUserId(int userId);
}
