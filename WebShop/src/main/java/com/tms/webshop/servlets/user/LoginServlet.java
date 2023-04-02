package com.tms.webshop.servlets.user;

import com.tms.webshop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static com.tms.webshop.service.UserServiceAware.CONTEXT_NAME;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = (UserService) request.getServletContext().getAttribute(CONTEXT_NAME);
        if (userService.validateUser(login, password)) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            request.getSession().setAttribute("cartProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("user", userService.getUserByLogin(login));
            response.sendRedirect("categories.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
