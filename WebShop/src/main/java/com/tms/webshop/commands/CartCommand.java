package com.tms.webshop.commands;

import com.tms.webshop.model.Product;
import com.tms.webshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tms.webshop.service.ProductServiceAware.CONTEXT_NAME;

public class CartCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();
        ProductService productService = (ProductService) request.getServletContext().getAttribute(CONTEXT_NAME);

        cartProductsMap.keySet().forEach(id -> {
            productsMap.put(productService.getProductById(id), cartProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        return "cart.jsp";
    }
}
