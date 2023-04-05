package com.tms.webshop.dao.database;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.utils.ConnectionWrapper;
import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tms.webshop.dao.BaseRepository.CONNECTION_POOL;

@Log4j2
public class OrderDaoDb implements OrderDao {
    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        ProductDaoDb productDaoDb = new ProductDaoDb();

        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String getOrdersSql = "SELECT * FROM orders WHERE user_id = ?";

            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(getOrdersSql)) {

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

        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String addOrderSql = "INSERT INTO orders (user_id, date, products) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(addOrderSql)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setDate(2, Date.valueOf(order.getDate()));
                preparedStatement.setObject(3, orderProductsMap);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error, while trying to save order.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }
}
