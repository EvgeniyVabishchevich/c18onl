package com.tms.webshop.servlets;

import com.tms.webshop.utils.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Constants.USER_LOGIN.equals(request.getParameter("login")) &&
                Constants.USER_PASSWORD.equals(request.getParameter("password"))) {
            request.getSession().setAttribute("basketProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("login", request.getParameter("login"));
            response.sendRedirect("/categories");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
