package com.tms.webshop.utilsDB;

import com.tms.webshop.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

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

    public int getCategoryId(String name) {
        try {
            String sql = "SELECT id FROM categories WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt("id") : -1;
        } catch (SQLException e) {
            System.out.println("Error, while trying to get category id from name." + e.getMessage());
        }
        return -1;
    }

    public List<Category> getCategories() {
        ProductDAO productDAO = new ProductDAO();
        List<Category> categories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setImageName(resultSet.getString("image_name"));
                category.setProductList(productDAO.findProductsByCategory(category.getId()));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        }
        return categories;
    }
}
