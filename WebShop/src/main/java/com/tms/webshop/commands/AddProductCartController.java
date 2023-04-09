package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tms.webshop.model.enums.Page.*;

public class AddProductCartController implements BaseCommandController {
    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        cartProductsMap.merge(Integer.parseInt(request.getParameter(RequestParams.PRODUCT_ID.getValue())), 1, Integer::sum);
        return CURRENT;
    }
}
