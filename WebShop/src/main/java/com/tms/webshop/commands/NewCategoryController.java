package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import static com.tms.webshop.model.enums.Page.*;

@Slf4j
public class NewCategoryController implements BaseCommandController {
    @Inject
    private ImageServiceAware imageService;

    @Inject
    private CategoryServiceAware categoryService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        try (InputStream fileStream = request.getPart("image").getInputStream()) {
            imageService.addImage(request.getParameter(RequestParams.IMAGE_NAME.getValue()), fileStream);

            categoryService.addCategory(request.getParameter(RequestParams.NAME.getValue()), request.getParameter(RequestParams.IMAGE_NAME.getValue()));
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
}
