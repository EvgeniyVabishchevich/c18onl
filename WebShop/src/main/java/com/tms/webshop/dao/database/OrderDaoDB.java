package com.tms.webshop.dao.database;

import com.tms.webshop.dao.DBConnectionContainer;
import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class OrderDaoDB implements OrderDao {
    private Connection connection;

    public OrderDaoDB() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    @Override
    public void addOrder(int userId, LocalDate orderDate, HashMap<Product, Integer> products) {

        try {
            int orderId = saveOrder(userId, orderDate);
            saveProducts(products.keySet());
            addOrderProductsRelations(orderId, products);
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new order" + e.getMessage());
        }
    }

    public int saveOrder(int userId, LocalDate orderDate) throws SQLException {
        String addOrderSql = "INSERT INTO orders (user_id, date) VALUES (?, ?) RETURNING id";

        PreparedStatement preparedStatement = connection.prepareStatement(addOrderSql);
        preparedStatement.setInt(1, userId);
        preparedStatement.setDate(2, Date.valueOf(orderDate));

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public void saveProducts(Set<Product> products) throws SQLException {
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
    }

    public void addOrderProductsRelations(int orderId, HashMap<Product, Integer> products) throws SQLException {
        String addRelationsSql = "INSERT INTO orders_products (order_id, product_id, amount) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(addRelationsSql);

        for (Product product : products.keySet()) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, products.get(product));
            preparedStatement.executeUpdate();
        }
    }
}
