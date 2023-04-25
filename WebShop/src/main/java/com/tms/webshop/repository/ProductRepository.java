package com.tms.webshop.repository;

import com.tms.webshop.model.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);

    List<Product> getProductsByCategoryId(int categoryId);

    Product getProductById(int id);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
