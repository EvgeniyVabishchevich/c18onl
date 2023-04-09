package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static com.tms.webshop.model.enums.Page.CURRENT;

@Slf4j
public class NewProductController implements BaseCommandController {

    @Inject
    private ImageServiceAware imageService;

    @Inject
    private CategoryServiceAware categoryService;

    @Inject
    private ProductServiceAware productService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream fileStream = request.getPart("image").getInputStream()) {
            imageService.addImage(request.getParameter(RequestParams.IMAGE_NAME.getValue()), fileStream);

            Product product = new Product(request.getParameter(RequestParams.NAME.getValue()),
                    request.getParameter(RequestParams.DESCRIPTION.getValue()),
                    new BigDecimal(request.getParameter(RequestParams.PRICE.getValue())),
                    request.getParameter(RequestParams.IMAGE_NAME.getValue()),
                    categoryService.getCategoryId(request.getParameter(RequestParams.CATEGORY.getValue())));

            productService.addProduct(product);
        } catch (ServletException | IOException e) {
            log.error("Error, while getting image from request", e);
        }
        return CURRENT;
    }

    public void setImageService(ImageServiceAware imageService) {
        this.imageService = imageService;
    }

    public void setCategoryService(CategoryServiceAware categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductServiceAware productService) {
        this.productService = productService;
    }
}
