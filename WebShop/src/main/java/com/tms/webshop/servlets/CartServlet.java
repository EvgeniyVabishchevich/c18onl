package com.tms.webshop.servlets;

import com.tms.webshop.model.Product;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/show-cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductService productService = (ProductService) request.getServletContext().getAttribute(ProductServiceAware.CONTEXT_NAME);

        cartProductsMap.keySet().forEach(id -> {
            productsMap.put(productService.getProductById(id), cartProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
