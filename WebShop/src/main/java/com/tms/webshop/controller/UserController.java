package com.tms.webshop.controller;

import com.tms.webshop.model.User;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.OrderServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.webshop.model.enums.Page.USER;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {
    private final OrderServiceAware orderService;
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView execute(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView(USER.getValue());
        modelAndView.addObject("orders", orderService.getOrdersByUserId(user.getId()));
        modelAndView.addObject("categories", categoryService.getCategories());
        return modelAndView;
    }
}
