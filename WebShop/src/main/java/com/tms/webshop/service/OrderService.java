package com.tms.webshop.service;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Order;

import java.util.List;

public class OrderService implements OrderServiceAware {
    @Inject
    private OrderDao orderDao;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(int userId, Order order) {
        orderDao.addOrder(userId, order);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
