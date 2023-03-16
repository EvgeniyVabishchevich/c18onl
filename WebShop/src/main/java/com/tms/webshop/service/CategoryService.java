package com.tms.webshop.service;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.database.CategoryDaoDb;
import com.tms.webshop.model.Category;

import java.util.List;

public class CategoryService implements CategoryServiceAware {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    @Override
    public void addCategory(String name, String imageName) {
        categoryDao.addCategory(name, imageName);
    }

    @Override
    public int getCategoryId(String name) {
        return categoryDao.getCategoryId(name);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }
}
