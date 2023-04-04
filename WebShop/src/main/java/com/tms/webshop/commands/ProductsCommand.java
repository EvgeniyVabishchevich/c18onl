package com.tms.webshop.commands;

import com.tms.webshop.model.Category;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductsCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
        String categoryName = request.getParameter("categoryName");
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            request.setAttribute("errorMsg", "No such category.");
            return "error404.jsp";
        } else {
            request.setAttribute("categoryName", categoryName);
            request.setAttribute("products", category.getProductList());
            return "products.jsp";
        }
    }
}
