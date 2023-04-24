package com.tms.webshop.controller;

import com.tms.webshop.model.Order;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.User;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;

import static com.tms.webshop.model.enums.Page.BUY;

@Controller
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final OrderServiceAware orderService;

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView Buy(@SessionAttribute HashMap<Integer, Integer> cartProductsMap, @SessionAttribute User user) {
        int userId = user.getId();

        HashMap<Product, Integer> products = new HashMap<>();
        cartProductsMap.keySet().forEach(id -> products.put(productService.getProductById(id), cartProductsMap.get(id)));

        Order order = new Order(LocalDate.now(), products);
        orderService.addOrder(userId, order);

        cartProductsMap.clear();

        return new ModelAndView(BUY.getValue());
    }

    @ModelAttribute
    public HashMap<Integer, Integer> cartProductsMap() {
        return new HashMap<>();
    }

    @ModelAttribute
    public User user() {
        return new User();
    }
}
