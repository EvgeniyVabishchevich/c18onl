package com.tms.webshop.commands;

import com.tms.webshop.model.User;
import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.tms.webshop.service.OrderServiceAware.CONTEXT_NAME;

public class UserController implements BaseCommandController {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(CONTEXT_NAME);

        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("orders", orderService.getOrdersByUserId(user.getId()));

        return Pages.USER.getValue();
    }
}
