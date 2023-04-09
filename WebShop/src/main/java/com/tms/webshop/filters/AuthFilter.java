package com.tms.webshop.filters;

import com.tms.webshop.model.enums.RequestParams;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.tms.webshop.model.enums.Command.REGISTER;
import static com.tms.webshop.model.enums.Command.SIGN_IN;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String uri = httpRequest.getRequestURI();

        String command = request.getParameter(RequestParams.COMMAND.getValue());

        if ((session == null || session.getAttribute("user") == null) && !uri.endsWith("login.jsp") &&
                !uri.endsWith("newAccount.jsp") && (command == null || !command.equals(SIGN_IN.getCommand()))
                && (command == null || !command.equals(REGISTER.getCommand()))
        ) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
