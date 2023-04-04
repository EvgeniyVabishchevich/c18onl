package com.tms.webshop.commands;

import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.User;
import com.tms.webshop.service.OrderService;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.HashMap;

public class BuyCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HashMap<Integer, Integer> basketProducts = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");

        int userId = ((User) request.getSession().getAttribute("user")).getId();

        OrderService orderService = (OrderService) request.getServletContext().getAttribute(OrderServiceAware.CONTEXT_NAME);
        ProductService productService = (ProductService) request.getServletContext().getAttribute(ProductServiceAware.CONTEXT_NAME);

        HashMap<Product, Integer> products = new HashMap<>();
        basketProducts.keySet().forEach(id -> products.put(productService.getProductById(id), basketProducts.get(id)));

        Order order = new Order(LocalDate.now(), products);
        orderService.addOrder(userId, order);

        basketProducts.clear();

        return "buy.jsp";
    }
}
