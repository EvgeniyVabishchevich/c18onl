package com.tms.webshop;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    private final String USER_LOGIN = "admin";
    private final String USER_PASSWORD = "admin";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(USER_LOGIN.equals(request.getParameter("login")) && USER_PASSWORD.equals(request.getParameter("password"))){
            request.getRequestDispatcher("/categories").forward(request, response);
        }
        else request.getRequestDispatcher("login.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
