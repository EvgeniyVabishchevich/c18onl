package com.tms.webshop.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/remove")
public class RemoveFromCartController {
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void removeProductFromCart(@SessionAttribute HashMap<Integer, Integer> cartProductsMap, @RequestParam(PRODUCT_ID) int productId) {
        if (cartProductsMap.get(productId) > 1) {
            cartProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            cartProductsMap.remove(productId);
        }
    }
}
