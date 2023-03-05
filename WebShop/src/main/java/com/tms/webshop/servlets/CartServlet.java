package com.tms.webshop.servlets;

import com.tms.webshop.model.Product;
import com.tms.webshop.utilsDB.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/show-cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> basketProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("basketProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductDAO productDAO = new ProductDAO();

        basketProductsMap.keySet().forEach(id -> {
            productsMap.put(productDAO.findProduct(id), basketProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
