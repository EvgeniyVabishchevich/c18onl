package com.tms.webshop.servlets;

import com.tms.webshop.utilsDB.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/categories")
public class CategoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("categories", new CategoryDAO().getCategories());
        try {
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (ServletException e) {
            System.out.println("Error while forward to products.jsp ");
        }
    }

    public void destroy() {
    }
}