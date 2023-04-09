package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.List;

import static com.tms.webshop.model.enums.Page.SEARCH_RESULT;

public class SearchController implements BaseCommandController {
    @Inject
    private ProductServiceAware productService;
    @Inject
    private CategoryServiceAware categoryService;

    private static final String ALL_CATEGORIES = "All";

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String searchRequest = request.getParameter(RequestParams.SEARCH_REQUEST.getValue());
        List<Product> searchResult = productService.getProductsByTextInNameAndDescription(searchRequest);

        String fromPrice = request.getParameter(RequestParams.FROM_PRICE.getValue());
        String toPrice = request.getParameter(RequestParams.TO_PRICE.getValue());
        String category = request.getParameter(RequestParams.CATEGORY.getValue());

        searchResult = searchResult.stream()
                .filter(product -> (fromPrice.equals("") || product.getPrice().compareTo(new BigDecimal(fromPrice)) >= 0)
                        && (toPrice.equals("") || product.getPrice().compareTo(new BigDecimal(toPrice)) <= 0)
                        && (category.equals(ALL_CATEGORIES)
                        || categoryService.getCategoryNameById(product.getCategoryId()).equals(category))).toList();
        request.setAttribute("products", searchResult);
        return SEARCH_RESULT;
    }

    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}
