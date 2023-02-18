package com.tms.webshop;

import java.io.*;
import java.util.List;

import com.tms.utilsDB.CategoryDAO;
import com.tms.webshop.model.Category;
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