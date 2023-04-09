package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tms.webshop.model.enums.Page.CART;

public class CartController implements BaseCommandController {

    @Inject
    private ProductServiceAware productService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Map<Product, Integer> productsMap = new HashMap<>();

        cartProductsMap.keySet().forEach(id -> {
            productsMap.put(productService.getProductById(id), cartProductsMap.get(id));
        });

        request.setAttribute("productsMap", productsMap);
        return CART;
    }

    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}
