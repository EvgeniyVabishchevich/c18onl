package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;
import java.util.UUID;

import static com.tms.webshop.service.UserServiceAware.CONTEXT_NAME;

public class SignInController implements BaseCommandController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(RequestParams.LOGIN.getValue());
        String password = request.getParameter(RequestParams.PASSWORD.getValue());
        UserService userService = (UserService) request.getServletContext().getAttribute(CONTEXT_NAME);
        if (userService.validateUser(login, password)) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            request.getSession().setAttribute("cartProductsMap", new HashMap<Integer, Integer>());
            request.getSession().setAttribute("user", userService.getUserByLogin(login));

            CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
            request.setAttribute("categories", categoryService.getCategories());
            return Pages.CATEGORIES.getValue();
        } else {
            return Pages.LOGIN.getValue();
        }
    }
}
