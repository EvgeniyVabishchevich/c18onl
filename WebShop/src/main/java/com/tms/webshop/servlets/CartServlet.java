package com.tms.webshop.servlets;

import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;
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
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductDao productDAODB = (ProductDao) request.getServletContext().getAttribute(ProductDao.CONTEXT_NAME);

        cartProductsMap.keySet().forEach(id -> {
            productsMap.put(productDAODB.findProduct(id), cartProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
