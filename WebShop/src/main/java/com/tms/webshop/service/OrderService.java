package com.tms.webshop.service;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.Order;

import java.util.List;

public class OrderService implements OrderServiceAware {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(int userId, Order order) {
        orderDao.addOrder(userId, order);
    }
}
