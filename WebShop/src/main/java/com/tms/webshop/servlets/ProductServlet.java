package com.tms.webshop.servlets;

import com.tms.webshop.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = (ArrayList<Category>) request.getSession().getAttribute("categories");
        String categoryName = request.getParameter("categoryName");
        Category category = findCategory(categories, categoryName);

        if (category == null) {
            request.setAttribute("errorMsg", "No such category.");
            request.getRequestDispatcher("error404.jsp").forward(request, response);
        } else {
            request.setAttribute("categoryName", categoryName);
            request.setAttribute("products", category.getProductList());
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

    public Category findCategory(List<Category> categories, String categoryName) {
        return categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .orElse(null);
    }
}
