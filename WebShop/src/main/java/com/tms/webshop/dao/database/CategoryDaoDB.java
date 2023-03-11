package com.tms.webshop.dao.database;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.DBConnectionContainer;
import com.tms.webshop.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoDB implements CategoryDao {
    private Connection connection;

    public CategoryDaoDB() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    private static final int NOT_FOUND = -1;

    @Override
    public void addCategory(String name, String imageName) {
        try {
            String sql = "INSERT INTO categories (name, image_name) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imageName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new category to database.");
        }
    }

    @Override
    public int getCategoryId(String name) {
        try {
            String sql = "SELECT id FROM categories WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt("id") : NOT_FOUND;
        } catch (SQLException e) {
            System.out.println("Error, while trying to get category id from name." + e.getMessage());
        }
        return NOT_FOUND;
    }

    @Override
    public List<Category> getCategories() {
        ProductDaoDB productDAODB = new ProductDaoDB();
        List<Category> categories = new ArrayList<>();

        try {
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
        } catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        }
        return categories;
    }
}
