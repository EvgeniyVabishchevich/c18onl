package com.tms.webshop.controller;

import com.tms.webshop.model.User;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.UserServiceAware;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

import static com.tms.webshop.model.enums.Page.CATEGORIES;
import static com.tms.webshop.model.enums.RequestParamsConstants.LOGIN;
import static com.tms.webshop.model.enums.RequestParamsConstants.PASSWORD;

@Controller
@RequestMapping("/login")
@SessionAttributes({"cartProductsMap", "user"})
public class SignInController {
    private UserServiceAware userService;
    private CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView goToLoginPage() {
        return new ModelAndView(Page.LOGIN.getValue());
    }

    @PostMapping
    public ModelAndView execute(@RequestParam(LOGIN) String login, @RequestParam(PASSWORD) String password) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.validateUser(login, password)) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            modelAndView.addObject("cartProductsMap", new HashMap<Integer, Integer>());
            modelAndView.addObject("user", userService.getUserByLogin(login));

            modelAndView.addObject("categories", categoryService.getCategories());
            modelAndView.setViewName(CATEGORIES.getValue());
        } else {
            modelAndView.setViewName(Page.LOGIN.getValue());
        }
        return modelAndView;
    }

    @ModelAttribute("cartProductsMap")
    public HashMap<Integer, Integer> cartProductsMap() {
        return new HashMap<>();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @Autowired
    public void setUserService(UserServiceAware userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }
}
