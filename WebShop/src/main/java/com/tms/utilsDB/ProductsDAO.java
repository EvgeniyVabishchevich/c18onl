package com.tms.utilsDB;

import com.tms.webshop.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
    private Connection connection;

    public ProductsDAO() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    public List<Product> findProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM products WHERE category_id = %d", categoryId));

            while (resultSet.next()) {
                products.add(getProductFromResult(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find products by category.");
        }

        return products;
    }

    public Product findProduct(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM products WHERE id = %d", id));

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
