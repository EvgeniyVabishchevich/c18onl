package com.tms.webshop.dao.database;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoDb implements CategoryDao {
    private static final Logger logger = LogManager.getLogger(CategoryDaoDb.class);

    @Override
    public void addCategory(String name, String imageName) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "INSERT INTO categories (name, image_name) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imageName);
            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error("Error, while trying to add new category to database.", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public int getCategoryId(String name) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT id FROM categories WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    ConnectionPool.getInstance().closeConnection(connection);

                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            logger.error("Error, while trying to get category id from name.", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }
        throw new RuntimeException("Wrong category name");
    }

    @Override
    public List<Category> getCategories() {
        ProductDaoDb productDAODB = new ProductDaoDb();
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM categories")) {

                    while (resultSet.next()) {
                        Category category = new Category();

                        category.setId(resultSet.getInt("id"));
                        category.setName(resultSet.getString("name"));
                        category.setImageName(resultSet.getString("image_name"));
                        category.setProductList(productDAODB.getProductsByCategoryId(category.getId()));

                        categories.add(category);
                    }
                }
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error("SQL exception", e);
        } catch (Exception e) {
            logger.error("Error, while trying to get or close connection.", e);
        }
        return categories;
    }
}
