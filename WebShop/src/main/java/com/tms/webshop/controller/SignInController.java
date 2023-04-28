package com.tms.webshop.controller;

import com.tms.webshop.model.User;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.UserServiceAware;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

import static com.tms.webshop.model.enums.Page.CATEGORIES;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@SessionAttributes({"cartProductsMap", "user"})
public class SignInController {
    private final UserServiceAware userService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView goToLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unknownUser", new User());
        modelAndView.setViewName(Page.LOGIN.getValue());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signIn(@Valid @ModelAttribute("unknownUser") User newUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Page.LOGIN.getValue());
            return modelAndView;
        }

        if (userService.validateUser(newUser.getLogin(), newUser.getPassword())) {
            ThreadContext.put("conversationId", UUID.randomUUID().toString());
            modelAndView.addObject("cartProductsMap", new HashMap<Integer, Integer>());
            modelAndView.addObject("user", userService.getUserByLogin(newUser.getLogin()));

            modelAndView.addObject("categories", categoryService.getCategories());
            modelAndView.setViewName(CATEGORIES.getValue());
        } else {
            modelAndView.setViewName(Page.LOGIN.getValue());
        }
        return modelAndView;
    }
}
