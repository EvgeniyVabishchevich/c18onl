package com.tms.webshop.shopFilters;

import com.tms.webshop.service.CategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter (urlPatterns = {"/categories.jsp", "/admin/admin.jsp", "/products"})
public class CategoriesListFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("categories", categoryDAO.getCategories());

        chain.doFilter(request, response);
    }
}
