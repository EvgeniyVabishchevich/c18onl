package com.tms.webshop.filters;

import com.tms.webshop.dao.CategoryDao;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter (urlPatterns = {"/categories.jsp", "/admin/admin.jsp", "/products"})
public class CategoriesListFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CategoryDao categoryDAO = new CategoryDao();
        request.setAttribute("categories", categoryDAO.getCategories());

        chain.doFilter(request, response);
    }
}
