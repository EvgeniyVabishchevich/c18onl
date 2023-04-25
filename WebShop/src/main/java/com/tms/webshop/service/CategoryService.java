package com.tms.webshop.service;

import com.tms.webshop.model.Category;
import com.tms.webshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceAware {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(String name, String imageName) {
        categoryRepository.addCategory(name, imageName);
    }

    @Override
    public int getCategoryId(String name) {
        return categoryRepository.getCategoryByName(name).getId();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public String getCategoryNameById(int id) {
        return categoryRepository.getCategoryById(id).getName();
    }
}
