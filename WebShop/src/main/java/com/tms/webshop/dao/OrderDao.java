package com.tms.webshop.dao;

import com.tms.webshop.model.Product;

import java.time.LocalDate;
import java.util.HashMap;

public interface OrderDao {
    String CONTEXT_NAME = "orderDao";
    void addOrder(int userId, LocalDate orderDate, HashMap<Product, Integer> products);
}
