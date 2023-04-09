package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.User;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.HashMap;

import static com.tms.webshop.model.enums.Page.*;

public class BuyController implements BaseCommandController {
    @Inject
    private OrderServiceAware orderService;

    @Inject
    private ProductServiceAware productService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        HashMap<Integer, Integer> basketProducts = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");

        int userId = ((User) request.getSession().getAttribute("user")).getId();

        HashMap<Product, Integer> products = new HashMap<>();
        basketProducts.keySet().forEach(id -> products.put(productService.getProductById(id), basketProducts.get(id)));

        Order order = new Order(LocalDate.now(), products);
        orderService.addOrder(userId, order);

        basketProducts.clear();

        return BUY;
    }

    public void setOrderService(OrderServiceAware orderService) {
        this.orderService = orderService;
    }

    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}
