package com.tms.webshop.service;

import com.tms.webshop.model.Order;

import java.util.List;

public interface OrderServiceAware {
    String CONTEXT_NAME = "orderService";
    List<Order> getOrdersByUserId(int userId);
    void addOrder(int userId, Order order);
}
