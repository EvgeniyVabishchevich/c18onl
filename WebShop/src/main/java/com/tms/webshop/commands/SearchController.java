package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.tms.webshop.model.enums.Page.*;

public class SearchController implements BaseCommandController {
    @Inject
    private ProductServiceAware productService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String searchRequest = request.getParameter(RequestParams.SEARCH_REQUEST.getValue());
        List<Product> searchResult = productService.getProductsByTextInNameAndDescription(searchRequest);
        request.setAttribute(RequestParams.PRODUCTS.getValue(), searchResult);
        return SEARCH_RESULT;
    }

    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}
