package com.tms.webshop.servlets;

import java.io.*;

import com.tms.webshop.utilsDB.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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