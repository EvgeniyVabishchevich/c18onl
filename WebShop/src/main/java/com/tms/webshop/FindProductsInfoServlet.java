package com.tms.webshop;

import com.tms.webshop.model.Category;
import com.tms.webshop.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/products")
public class FindProductsInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        if(categoryName != null){
            List<Category> categories = (ArrayList<Category>) request.getSession().getAttribute("categories");

            request.setAttribute("categoryName", categoryName);
            request.setAttribute("products", getProductsByCategoryName(categories, categoryName));
        }
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
    public List<Product> getProductsByCategoryName(List<Category> categories, String categoryName){
        return categories.stream()
                .filter(c -> c.getName().equals(categoryName))
                .findFirst()
                .orElse(null)
                .getProductList();
    }
}
