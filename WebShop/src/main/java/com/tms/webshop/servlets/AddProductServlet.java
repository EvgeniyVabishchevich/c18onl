package com.tms.webshop.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet(name = "AddProductServlet", value = "/add-product")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> basketProductsMap = (HashMap<Integer, Integer>) request.getSession().getAttribute("basketProductsMap");
        basketProductsMap.merge(Integer.parseInt(request.getParameter("productId")), 1, Integer::sum);
    }
}
