package com.tms.webshop.commands;

import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tms.webshop.service.ProductServiceAware.CONTEXT_NAME;

public class CartController implements BaseCommandController {
    @Override
    public Pages execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductService productService = (ProductService) request.getServletContext().getAttribute(CONTEXT_NAME);

        cartProductsMap.keySet().forEach(id -> {
            productsMap.put(productService.getProductById(id), cartProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        return Pages.CART;
    }
}
