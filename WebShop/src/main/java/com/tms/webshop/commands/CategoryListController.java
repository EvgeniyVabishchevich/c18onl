package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryListController implements BaseCommandController {
    @Inject
    private CategoryServiceAware categoryService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(RequestParams.CATEGORIES.getValue(), categoryService.getCategories());
        return Page.fromString(request.getParameter(RequestParams.PAGE.getValue()));
    }

    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }
}
