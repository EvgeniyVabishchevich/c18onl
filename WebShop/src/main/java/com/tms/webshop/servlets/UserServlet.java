package com.tms.webshop.servlets;

import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute(OrderDao.CONTEXT_NAME);

        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("orders", orderDao.getOrders(user.getId()));

        System.out.println(orderDao.getOrders(user.getId()).size());

        request.getRequestDispatcher("user.jsp").forward(request, response);
    }
}
