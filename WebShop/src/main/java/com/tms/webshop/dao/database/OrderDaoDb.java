package com.tms.webshop.dao.database;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Log4j2
public class OrderDaoDb implements OrderDao {
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        ProductDaoDb productDaoDb = new ProductDaoDb();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String getOrdersSql = "SELECT * FROM orders WHERE user_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(getOrdersSql)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Order order = new Order();
                        order.setId(resultSet.getInt(1));
                        order.setDate(resultSet.getDate(3).toLocalDate());

                        Map<String, String> productsMap = (Map<String, String>) resultSet.getObject(4);
                        Map<Product, Integer> products = new HashMap<>();
                        for (String id : productsMap.keySet()) {
                            products.put(productDaoDb.getProductById(Integer.parseInt(id)), Integer.parseInt(productsMap.get(id)));
                        }
                        order.setProducts(products);

                        orders.add(order);
                    }
                }
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            log.error("Error, while trying to get user orders from database", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
        return orders;
    }

    @Override
    public void addOrder(int userId, Order order) {
        saveOrder(userId, order);
    }

    public void saveOrder(int userId, Order order) {
        HashMap<String, String> orderProductsMap = new HashMap<>();
        order.getProducts().keySet().forEach(product -> {
            orderProductsMap.put(String.valueOf(product.getId()), String.valueOf(order.getProducts().get(product)));
        });

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String addOrderSql = "INSERT INTO orders (user_id, date, products) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(addOrderSql)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setDate(2, Date.valueOf(order.getDate()));
                preparedStatement.setObject(3, orderProductsMap);

                preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            log.error("Error, while trying to save order.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }
}
