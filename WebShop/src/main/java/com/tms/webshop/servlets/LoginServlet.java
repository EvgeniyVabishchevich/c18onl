package com.tms.webshop.servlets;

import com.tms.webshop.utils.Constants;
import com.tms.webshop.utilsDB.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        if (userDAO.validateUser(login, password)){
            request.getSession().setAttribute("basketProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("user_type", userDAO.getUserType(login, password));
            response.sendRedirect("/categories");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
