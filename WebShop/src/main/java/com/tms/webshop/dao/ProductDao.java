package com.tms.webshop.dao;

import com.tms.webshop.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    String CONTEXT_NAME = "productDao";

    void addProduct(String name, String description, BigDecimal price, String imageName, int categoryId);

    List<Product> findProductsByCategory(int categoryId);

    Product findProduct(int id);
}
