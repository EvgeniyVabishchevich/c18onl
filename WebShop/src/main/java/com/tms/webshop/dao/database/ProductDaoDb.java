package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDb extends AllProductDaoDb implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoDb.class);

    public ProductDaoDb() {
        super("products");
    }

    @Override
    public void addProduct(Product product) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "INSERT INTO products (name, description, price, image_name, category_id) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.setString(4, product.getImageName());
                preparedStatement.setInt(5, product.getCategoryId());
                preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Error, while trying to add new product to database.", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
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
            logger.error("SQL exception, while trying to find products by category.", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }

        return products;
    }
}
