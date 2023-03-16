package com.tms.webshop.service;

import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;

import java.util.List;

public class ProductService implements ProductServiceAware {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return productDao.getProductsByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
