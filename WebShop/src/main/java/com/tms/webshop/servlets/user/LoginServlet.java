package com.tms.webshop.servlets.user;

import com.tms.webshop.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDao userDAO = new UserDao();
        if (userDAO.validateUser(login, password)) {
            request.getSession().setAttribute("cartProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("user", userDAO.getUser(login));
            response.sendRedirect("categories.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
