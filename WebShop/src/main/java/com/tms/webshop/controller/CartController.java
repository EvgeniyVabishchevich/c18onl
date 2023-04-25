package com.tms.webshop.controller;

import com.tms.webshop.model.enums.RequestParamsConstants;
import com.tms.webshop.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static com.tms.webshop.model.enums.Page.CART;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
@SessionAttributes("cartProductsMap")
public class CartController {

    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showCart(@SessionAttribute HashMap<Integer, Integer> cartProductsMap) {
        ModelAndView modelAndView = new ModelAndView(CART.getValue());
        modelAndView.addObject(RequestParamsConstants.PRODUCTS_MAP, productService.getProductsByIds(cartProductsMap));
        return modelAndView;
    }

    @ModelAttribute
    public HashMap<Integer, Integer> cartProductsMap() {
        return new HashMap<>();
    }
}
