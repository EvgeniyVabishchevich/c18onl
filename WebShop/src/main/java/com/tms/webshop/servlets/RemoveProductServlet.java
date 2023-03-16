package com.tms.webshop.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/remove-product")
public class RemoveProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> cartProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("cartProductsMap");
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        if (cartProductsMap.get(productId) > 1) {
            cartProductsMap.compute(productId, (key, value) -> value - 1);
        } else {
            cartProductsMap.remove(productId);
        }
    }
}
