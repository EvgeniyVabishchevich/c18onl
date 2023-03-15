package com.tms.webshop.servlets;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@WebServlet(value = "/buy")
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<Integer, Integer> basketProducts = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");

        int userId = ((User) request.getSession().getAttribute("user")).getId();

        OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute(OrderDao.CONTEXT_NAME);
        ProductDao productDao = (ProductDao) request.getServletContext().getAttribute(ProductDao.CONTEXT_NAME);

        HashMap<Product, Integer> products = new HashMap<>();
        basketProducts.keySet().forEach(id -> products.put(productDao.findProduct(id), basketProducts.get(id)));

        orderDao.addOrder(userId, LocalDate.now(), products);

        basketProducts.clear();

        request.getRequestDispatcher("buy.jsp").forward(request, response);
    }
}
