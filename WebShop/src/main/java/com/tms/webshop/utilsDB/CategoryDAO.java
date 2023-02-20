package com.tms.webshop.utilsDB;

import com.tms.webshop.model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    public List<Category> getCategories() {
        ProductsDAO productsDAO = new ProductsDAO();
        List<Category> categories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");

            while (resultSet.next()) {
                Category category = new Category();

                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setImageName(resultSet.getString("image_name"));
                category.setProductList(productsDAO.findProductsByCategory(category.getId()));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        }
        return categories;
    }
}
