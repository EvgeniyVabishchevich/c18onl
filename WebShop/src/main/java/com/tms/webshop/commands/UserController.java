package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.User;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.OrderServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserController implements BaseCommandController {
    @Inject
    private OrderServiceAware orderService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("orders", orderService.getOrdersByUserId(user.getId()));

        return Page.USER;
    }

    public void setOrderService(OrderServiceAware orderService) {
        this.orderService = orderService;
    }
}
