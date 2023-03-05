package com.tms.webshop.shopFilters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String uri = httpRequest.getRequestURI();

        if ((session == null || session.getAttribute("user") == null) && !uri.endsWith("login.jsp") && !uri.endsWith("/login")
                && !uri.endsWith("newAccount.jsp") && !uri.endsWith("create-account")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
