package com.tms.webshop.dao.database;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDaoDb implements OrderDao {
    public OrderDaoDb() {
    }

    @Override
    public List<Order> getOrders(int userId) {
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String getOrdersSql = "SELECT * FROM orders WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(getOrdersSql);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setDate(resultSet.getDate(3).toLocalDate());

                Map<String, String> productsMap = (Map<String, String>) resultSet.getObject(4);
                Map<Product, Integer> products = new HashMap<>();
                for (String id : productsMap.keySet()) {
                    products.put(getProduct(Integer.parseInt(id)), Integer.parseInt(productsMap.get(id)));
                }
                order.setProducts(products);

                orders.add(order);
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to get user orders from database");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return orders;
    }

    private Product getProduct(int productId) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            ConnectionPool.getInstance().closeConnection(connection);

            if (resultSet.next()) {
                return getProductFromResult(resultSet);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find product by id.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return null;
    }

    private Product getProductFromResult(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageName(resultSet.getString("image_name"));

        return product;
    }

    @Override
    public void addOrder(int userId, LocalDate orderDate, HashMap<Product, Integer> products) {
        saveOrder(userId, orderDate, products);
        saveProducts(products.keySet());
    }

    public void saveOrder(int userId, LocalDate orderDate, HashMap<Product, Integer> products) {
        HashMap<String, String> productsHstore = new HashMap<>();
        products.keySet().forEach(product -> {
            productsHstore.put(String.valueOf(product.getId()), String.valueOf(products.get(product)));
        });

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String addOrderSql = "INSERT INTO orders (user_id, date, products) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(addOrderSql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, Date.valueOf(orderDate));
            preparedStatement.setObject(3, productsHstore);

            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to save order.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }

    public void saveProducts(Set<Product> products) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String addProductsRecordsSql = "INSERT INTO products_records (id, name, description, price, image_name) " +
                    "VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";

            PreparedStatement preparedStatement = connection.prepareStatement(addProductsRecordsSql);

            for (Product product : products) {
                preparedStatement.setInt(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setBigDecimal(4, product.getPrice());
                preparedStatement.setString(5, product.getImageName());
                preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to save products records.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }
}
