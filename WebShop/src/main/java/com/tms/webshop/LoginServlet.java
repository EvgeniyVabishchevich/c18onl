package com.tms.webshop;

import com.tms.utils.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Constants.USER_LOGIN.equals(request.getParameter("login")) &&
                Constants.USER_PASSWORD.equals(request.getParameter("password"))) {
            request.getSession().setAttribute("login", request.getParameter("login"));
            response.sendRedirect("/categories");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
