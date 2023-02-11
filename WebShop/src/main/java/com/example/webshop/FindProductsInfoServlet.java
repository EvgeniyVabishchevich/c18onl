package com.example.webshop;

import com.example.webshop.model.Category;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/FindProductsInfoServlet")
public class FindProductsInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("category") != null){
            List<Category> categories = (ArrayList<Category>) request.getSession().getAttribute("categories");

            request.setAttribute("category", request.getParameter("category"));
            request.setAttribute("products", categories.stream().filter(c -> c.getName().equals(
                    request.getParameter("category"))).findFirst().get().getProductList());

            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
