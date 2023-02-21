package com.tms.webshop.servlets;

import com.tms.webshop.utilsDB.ProductsDAO;
import com.tms.webshop.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BasketServlet", value = "/show-basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> basketProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("basketProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductsDAO productsDAO = new ProductsDAO();

        basketProductsMap.keySet().forEach(id -> {
            productsMap.put(productsDAO.findProduct(id), basketProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        request.getRequestDispatcher("basket.jsp").forward(request, response);
    }
}
