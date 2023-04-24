package com.tms.webshop.controller;

import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.CategoryServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.tms.webshop.model.enums.RequestParamsConstants.CATEGORIES;
import static com.tms.webshop.model.enums.RequestParamsConstants.PAGE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryListController {
    private final CategoryServiceAware categoryService;

    @GetMapping
    public ModelAndView setCategoryList(@RequestParam(PAGE) String page) {
        ModelAndView modelAndView = new ModelAndView(Page.fromString(page).getValue());
        modelAndView.addObject(CATEGORIES, categoryService.getCategories());
        return modelAndView;
    }
}
