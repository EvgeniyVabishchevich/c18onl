package com.tms.webshop.dao.database;

import com.tms.webshop.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllProductDaoDb {
    private static final Logger logger = LogManager.getLogger(AllProductDaoDb.class);

    private final String tableName;

    public AllProductDaoDb(String tableName) {
        this.tableName = tableName;
    }

    public Product getProductById(int productId) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    ConnectionPool.getInstance().closeConnection(connection);

                    if (resultSet.next()) {
                        return getProductFromResult(resultSet);
                    } else {
                        throw new SQLException("There is no product with id - " + productId);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("SQL exception, while trying to find product by id.", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }
        return null;
    }

    protected Product getProductFromResult(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageName(resultSet.getString("image_name"));

        return product;
    }
}
