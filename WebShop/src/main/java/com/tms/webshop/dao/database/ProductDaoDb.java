package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDb implements ProductDao {
    public ProductDaoDb() {
    }

    @Override
    public void addProduct(String name, String description, BigDecimal price, String imageName, int categoryId) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "INSERT INTO products (name, description, price, image_name, category_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setString(4, imageName);
            preparedStatement.setInt(5, categoryId);
            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new product to database.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }

    @Override
    public List<Product> findProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM products WHERE category_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResult(resultSet));
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find products by category.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }

        return products;
    }

    @Override
    public Product findProduct(int id) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

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

}
