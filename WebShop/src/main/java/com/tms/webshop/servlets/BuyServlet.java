package com.tms.webshop.servlets;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.User;
import com.tms.webshop.service.OrderService;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
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

        OrderService orderService = (OrderService) request.getServletContext().getAttribute(OrderServiceAware.CONTEXT_NAME);
        ProductService productService = (ProductService) request.getServletContext().getAttribute(ProductServiceAware.CONTEXT_NAME);

        HashMap<Product, Integer> products = new HashMap<>();
        basketProducts.keySet().forEach(id -> products.put(productService.getProductById(id), basketProducts.get(id)));

        Order order = new Order(LocalDate.now(), products);
        orderService.addOrder(userId, order);

        basketProducts.clear();

        request.getRequestDispatcher("buy.jsp").forward(request, response);
    }
}
