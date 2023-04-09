package com.tms.webshop.service;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.model.Category;
import com.tms.webshop.model.Inject;

import java.util.List;

public class CategoryService implements CategoryServiceAware {
    @Inject
    private CategoryDao categoryDao;

    @Override
    public void addCategory(String name, String imageName) {
        categoryDao.addCategory(name, imageName);
    }

    @Override
    public int getCategoryId(String name) {
        return categoryDao.getCategoryByName(name).getId();
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

    @Override
    public String getCategoryNameById(int id) {
        return categoryDao.getCategoryById(id).getName();
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
