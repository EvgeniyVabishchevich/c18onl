package com.tms.webshop.commands;

import com.tms.webshop.model.Category;
import com.tms.webshop.model.Inject;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.tms.webshop.model.enums.Page.ERROR404;
import static com.tms.webshop.model.enums.Page.PRODUCTS;

public class ProductsController implements BaseCommandController {
    @Inject
    private CategoryServiceAware categoryService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter(RequestParams.CATEGORY_NAME.getValue());
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            request.setAttribute("errorMsg", "No such category.");
            return ERROR404;
        } else {
            request.setAttribute("categoryName", categoryName);
            request.setAttribute("products", category.getProductList());
            return PRODUCTS;
        }
    }

    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }
}
