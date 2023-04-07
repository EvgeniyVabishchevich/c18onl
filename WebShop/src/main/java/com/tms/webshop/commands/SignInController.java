package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.UserServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.UUID;

public class SignInController implements BaseCommandController {

    @Inject
    private UserServiceAware userService;

    @Inject
    private CategoryServiceAware categoryService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(RequestParams.LOGIN.getValue());
        String password = request.getParameter(RequestParams.PASSWORD.getValue());
        if (userService.validateUser(login, password)) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            request.getSession().setAttribute("cartProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("user", userService.getUserByLogin(login));

            request.setAttribute("categories", categoryService.getCategories());
            return Page.CATEGORIES;
        } else {
            return Page.LOGIN;
        }
    }

    public void setUserService(UserServiceAware userService) {
        this.userService = userService;
    }

    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }
}
