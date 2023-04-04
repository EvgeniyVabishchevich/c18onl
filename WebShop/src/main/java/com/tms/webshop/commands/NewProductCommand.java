package com.tms.webshop.commands;

import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@Log4j2
public class NewProductCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream fileStream = request.getPart("image").getInputStream()) {
            ServletContext servletContext = request.getServletContext();

            ImageService imageService = (ImageService) servletContext.getAttribute(ImageServiceAware.CONTEXT_NAME);
            imageService.addImage(request.getParameter(RequestParams.IMAGE_NAME.getValue()), fileStream);

            CategoryService categoryService = (CategoryService) servletContext.getAttribute(CategoryServiceAware.CONTEXT_NAME);
            ProductService productService = (ProductService) servletContext.getAttribute(ProductServiceAware.CONTEXT_NAME);

            Product product = new Product(request.getParameter(RequestParams.NAME.getValue()),
                    request.getParameter(RequestParams.DESCRIPTION.getValue()),
                    new BigDecimal(request.getParameter(RequestParams.Price.getValue())),
                    request.getParameter(RequestParams.IMAGE_NAME.getValue()),
                    categoryService.getCategoryId(request.getParameter(RequestParams.CATEGORY.getValue())));

            productService.addProduct(product);
        } catch (ServletException | IOException e) {
            log.error("Error, while getting image from request", e);
        }
        return null;
    }
}
