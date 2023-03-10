package com.tms.webshop.dao.database;

import com.tms.webshop.dao.DBConnectionContainer;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB implements ProductDao {
    private Connection connection;

    public ProductDaoDB() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    @Override
    public void addProduct(String name, String description, BigDecimal price, String imageName, int categoryId) {
        try {
            String sql = "INSERT INTO products (name, description, price, image_name, category_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setString(4, imageName);
            preparedStatement.setInt(5, categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new product to database.");
        }
    }

    @Override
    public List<Product> findProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products WHERE category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResult(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find products by category.");
        }

        return products;
    }

    @Override
    public Product findProduct(int id) {
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getProductFromResult(resultSet);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find product by id.");
        }
        return null;
    }

}
