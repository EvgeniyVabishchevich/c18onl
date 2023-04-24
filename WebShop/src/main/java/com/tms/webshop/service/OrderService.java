package com.tms.webshop.service;

import com.tms.webshop.model.Order;
import com.tms.webshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceAware {
    private final OrderRepository orderDao;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(int userId, Order order) {
        orderDao.addOrder(userId, order);
    }
}
