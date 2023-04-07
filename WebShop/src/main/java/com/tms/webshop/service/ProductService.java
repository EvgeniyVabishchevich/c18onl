package com.tms.webshop.service;

import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Product;

import java.util.List;

public class ProductService implements ProductServiceAware {
    @Inject
    private ProductDao productDao;

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

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
