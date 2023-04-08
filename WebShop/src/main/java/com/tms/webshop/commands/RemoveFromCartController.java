package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class RemoveFromCartController implements BaseCommandController {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Integer productId = Integer.parseInt(request.getParameter(RequestParams.PRODUCT_ID.getValue()));
        if (cartProductsMap.get(productId) > 1) {
            cartProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            cartProductsMap.remove(productId);
        }
        return Page.CURRENT;
    }
}
