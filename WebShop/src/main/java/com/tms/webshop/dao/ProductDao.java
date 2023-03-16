package com.tms.webshop.dao;

import com.tms.webshop.model.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);

    List<Product> getProductsByCategoryId(int categoryId);

    Product getProductById(int id);
}
