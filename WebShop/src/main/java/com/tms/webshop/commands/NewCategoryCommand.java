package com.tms.webshop.commands;

import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class NewCategoryCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream fileStream = request.getPart("image").getInputStream()) {
            ImageService imageService = (ImageService) request.getServletContext().getAttribute(ImageServiceAware.CONTEXT_NAME);
            imageService.addImage(request.getParameter(RequestParams.IMAGE_NAME.getValue()), fileStream);

            CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
            categoryService.addCategory(request.getParameter(RequestParams.NAME.getValue()), request.getParameter(RequestParams.IMAGE_NAME.getValue()));
        } catch (ServletException | IOException e) {
            log.error("Error, while getting image from request", e);
        }
        return null;
    }
}
