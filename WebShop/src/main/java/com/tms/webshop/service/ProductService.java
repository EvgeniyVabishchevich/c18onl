package com.tms.webshop.service;

import com.tms.webshop.model.Product;
import com.tms.webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceAware {
    private final ProductRepository productDao;

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

    @Override
    public Map<Product, Integer> getProductsByIds(HashMap<Integer, Integer> idAmountMap) {
        Map<Product, Integer> productsMap = new HashMap<>();

        idAmountMap.keySet().forEach(id -> {
            productsMap.put(getProductById(id), idAmountMap.get(id));
        });

        return productsMap;
    }

    @Override
    public List<Product> getProductsByTextInNameAndDescription(String searchRequest) {
        return productDao.getProductsByTextInNameAndDescription(searchRequest);
    }
}
