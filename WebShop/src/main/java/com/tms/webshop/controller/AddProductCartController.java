package com.tms.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;

import static com.tms.webshop.model.enums.RequestParamsConstants.PRODUCT_ID;

@Controller
@RequestMapping("/add-product")
public class AddProductCartController {
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void addProduct(@RequestParam(PRODUCT_ID) int productId, @SessionAttribute("cartProductsMap") HashMap<Integer, Integer> cartProductsMap) {
        cartProductsMap.merge(productId, 1, Integer::sum);
    }
}
