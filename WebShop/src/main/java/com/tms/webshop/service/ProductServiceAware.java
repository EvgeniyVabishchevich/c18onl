package com.tms.webshop.service;

import com.tms.webshop.model.Product;

import java.util.List;

public interface ProductServiceAware {
    String CONTEXT_NAME = "productService";

    void addProduct(Product product);

    List<Product> getProductsByCategoryId(int categoryId);

    Product getProductById(int id);

    List<Product> getProductsByTextInNameAndDescription(String searchRequest);
}
