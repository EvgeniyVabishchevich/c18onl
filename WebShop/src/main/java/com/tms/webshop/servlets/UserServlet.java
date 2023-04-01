package com.tms.webshop.servlets;

import com.tms.webshop.model.User;
import com.tms.webshop.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.tms.webshop.service.OrderServiceAware.CONTEXT_NAME;

@WebServlet(value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(CONTEXT_NAME);

        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("orders", orderService.getOrdersByUserId(user.getId()));

        request.getRequestDispatcher("user.jsp").forward(request, response);
    }
}
