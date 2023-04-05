package com.tms.webshop.commands;

import com.tms.webshop.model.Category;
import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductsController implements BaseCommandController {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
        String categoryName = request.getParameter(RequestParams.CATEGORY_NAME.getValue());
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            request.setAttribute("errorMsg", "No such category.");
            return Pages.ERROR404.getValue();
        } else {
            request.setAttribute("categoryName", categoryName);
            request.setAttribute("products", category.getProductList());
            return Pages.PRODUCTS.getValue();
        }
    }
}
