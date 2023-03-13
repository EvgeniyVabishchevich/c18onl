package com.tms.webshop.dao;

import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface OrderDao {
    String CONTEXT_NAME = "orderDao";
    void addOrder(int userId, LocalDate orderDate, HashMap<Product, Integer> products);
    List<Order> getOrders(int userId);
}
