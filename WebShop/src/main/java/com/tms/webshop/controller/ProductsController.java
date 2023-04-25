package com.tms.webshop.controller;

import com.tms.webshop.model.Category;
import com.tms.webshop.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.webshop.model.enums.Page.ERROR404;
import static com.tms.webshop.model.enums.Page.PRODUCTS;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final CategoryServiceAware categoryService;

    @GetMapping("/{categoryName}")
    public ModelAndView getCategoryProducts(@PathVariable String categoryName) {
        ModelAndView modelAndView = new ModelAndView();

        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            modelAndView.addObject("errorMsg", "No such category");
            modelAndView.setViewName(ERROR404.getValue());
        } else {
            modelAndView.addObject("category", category);
            modelAndView.setViewName(PRODUCTS.getValue());
        }
        return modelAndView;
    }
}
