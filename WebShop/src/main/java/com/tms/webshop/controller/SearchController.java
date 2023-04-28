package com.tms.webshop.controller;

import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.RequestParamsConstants;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static com.tms.webshop.model.enums.Page.SEARCH;
import static com.tms.webshop.model.enums.Page.SEARCH_RESULT;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final ProductServiceAware productService;
    private final CategoryServiceAware categoryService;

    private static final String ALL_CATEGORIES = "All";

    @GetMapping
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(SEARCH.getValue());
        return modelAndView;
    }

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelAndView searchResult(@RequestParam(RequestParamsConstants.SEARCH_REQUEST) String searchRequest,
                                     @RequestParam(RequestParamsConstants.FROM_PRICE) String fromPrice,
                                     @RequestParam(RequestParamsConstants.TO_PRICE) String toPrice,
                                     @RequestParam(RequestParamsConstants.CATEGORY) String category) {
        ModelAndView modelAndView = new ModelAndView();

        List<Product> searchResult = productService.getProductsByTextInNameAndDescription(searchRequest);

        searchResult = searchResult.stream()
                .filter(product -> {
                    boolean notTooSmallPrice = "".equals(fromPrice) || product.getPrice().compareTo(new BigDecimal(fromPrice)) >= 0;
                    boolean notTooBigPrice = "".equals(toPrice) || product.getPrice().compareTo(new BigDecimal(toPrice)) <= 0;
                    boolean isCategoryOk = category.equals(ALL_CATEGORIES)
                            || categoryService.getCategoryNameById(product.getCategoryId()).equals(category);

                    return notTooSmallPrice && notTooBigPrice && isCategoryOk;
                }).toList();

        modelAndView.addObject("products", searchResult);
        modelAndView.setViewName(SEARCH_RESULT.getValue());
        return modelAndView;
    }
}
