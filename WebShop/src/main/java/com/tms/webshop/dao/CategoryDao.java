package com.tms.webshop.dao;

import com.tms.webshop.model.Category;

import java.util.List;

public interface CategoryDao {
    String CONTEXT_NAME = "categoryDao";
    void addCategory(String name, String imageName);

    int getCategoryId(String name);

    List<Category> getCategories();
}
