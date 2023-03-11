package com.tms.webshop.dao;

import com.tms.webshop.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    String CONTEXT_NAME = "productDao";

    void addProduct(String name, String description, BigDecimal price, String imageName, int categoryId);

    List<Product> findProductsByCategory(int categoryId);

    Product findProduct(int id);

    default Product getProductFromResult(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageName(resultSet.getString("image_name"));

        return product;
    }
}
