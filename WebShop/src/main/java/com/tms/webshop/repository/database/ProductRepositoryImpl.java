package com.tms.webshop.repository.database;

import com.tms.webshop.model.Product;
import com.tms.webshop.repository.ProductRepository;
import com.tms.webshop.repository.utils.ConnectionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tms.webshop.repository.BaseRepository.CONNECTION_POOL;

@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void addProduct(Product product) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "INSERT INTO products (name, description, price, image_name, category_id) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {

                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.setString(4, product.getImageName());
                preparedStatement.setInt(5, product.getCategoryId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error, while trying to add new product to database.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();

        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM products WHERE category_id = ?";
            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setInt(1, categoryId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        products.add(getProductFromResult(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            log.error("SQL exception, while trying to find products by category.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }

        return products;
    }

    public Product getProductById(int productId) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setInt(1, productId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getProductFromResult(resultSet);
                    } else {
                        throw new SQLException("There is no product with id - " + productId);
                    }
                }
            }
        } catch (SQLException e) {
            log.error("SQL exception, while trying to find product by id.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
        return null;
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        List<Product> products = new ArrayList<>();

        String searchPattern = "%" + searchRequest + "%";
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, searchPattern);
                preparedStatement.setString(2, searchPattern);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        products.add(getProductFromResult(resultSet));
                    }
                }
            } catch (SQLException e) {
                log.error("Error, while trying to search products in database.", e);
            }
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
        return products;
    }

    protected Product getProductFromResult(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageName(resultSet.getString("image_name"));
        product.setCategoryId(resultSet.getInt("category_id"));

        return product;
    }
}
