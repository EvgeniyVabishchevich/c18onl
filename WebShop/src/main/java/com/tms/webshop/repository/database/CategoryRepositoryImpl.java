package com.tms.webshop.repository.database;

import com.tms.webshop.model.Category;
import com.tms.webshop.repository.CategoryRepository;
import com.tms.webshop.repository.utils.ConnectionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.tms.webshop.repository.BaseRepository.CONNECTION_POOL;

@Slf4j
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public void addCategory(String name, String imageName) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "INSERT INTO categories (name, image_name) VALUES (?, ?)";

            PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imageName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error, while trying to add new category to database.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public List<Category> getCategories() {
        ProductRepositoryImpl productDaoDb = new ProductRepositoryImpl();
        List<Category> categories = new ArrayList<>();

        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            try (Statement statement = connectionWrapper.getConnection().createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM categories")) {

                    while (resultSet.next()) {
                        Category category = new Category();

                        category.setId(resultSet.getInt("id"));
                        category.setName(resultSet.getString("name"));
                        category.setImageName(resultSet.getString("image_name"));
                        category.setProductList(productDaoDb.getProductsByCategoryId(category.getId()));

                        categories.add(category);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("SQL exception", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM categories WHERE name = ?";
            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getCategoryFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error, while trying to get category by name.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection", e);
        }
        return null;
    }

    @Override
    public Category getCategoryById(int id) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM categories WHERE id = ?";
            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getCategoryFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error, while trying to get category by name.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection", e);
        }
        return null;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        ProductRepositoryImpl productDaoDb = new ProductRepositoryImpl();

        Category category = new Category();

        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setImageName(resultSet.getString("image_name"));
        category.setProductList(productDaoDb.getProductsByCategoryId(category.getId()));

        return category;
    }
}
