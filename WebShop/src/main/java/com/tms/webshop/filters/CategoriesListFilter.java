package com.tms.webshop.filters;

import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter (urlPatterns = {"/categories.jsp", "/admin/admin.jsp"})
public class CategoriesListFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
        request.setAttribute("categories", categoryService.getCategories());

        chain.doFilter(request, response);
    }
}
