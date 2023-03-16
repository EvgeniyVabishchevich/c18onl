package com.tms.webshop.dao;

import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface OrderDao {
    void addOrder(int userId, Order order);

    List<Order> getOrdersByUserId(int userId);
}
