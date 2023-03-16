package com.tms.webshop.service;

import com.tms.webshop.model.Category;

import java.util.List;

public interface CategoryServiceAware {
    String CONTEXT_NAME = "categoryService";
    void addCategory(String name, String imageName);

    int getCategoryId(String name);

    List<Category> getCategories();
}
