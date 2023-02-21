package com.tms.webshop.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RemoveProductServlet", value = "/remove-product")
public class RemoveProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> basketProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("basketProductsMap");
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        if (basketProductsMap.get(productId) > 1) {
            basketProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            basketProductsMap.remove(productId);
        }
    }
}
