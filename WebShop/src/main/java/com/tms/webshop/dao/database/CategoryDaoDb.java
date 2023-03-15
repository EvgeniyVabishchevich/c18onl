package com.tms.webshop.dao.database;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoDb implements CategoryDao {
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
            System.out.println("Error, while trying to add new category to database.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }

    @Override
    public int getCategoryId(String name) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT id FROM categories WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            ConnectionPool.getInstance().closeConnection(connection);

            return resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("Error, while trying to get category id from name." + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        throw new RuntimeException("Wrong category name");
    }

    @Override
    public List<Category> getCategories() {
        ProductDaoDb productDAODB = new ProductDaoDb();
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setImageName(resultSet.getString("image_name"));
                category.setProductList(productDAODB.findProductsByCategory(category.getId()));

                categories.add(category);
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return categories;
    }
}
