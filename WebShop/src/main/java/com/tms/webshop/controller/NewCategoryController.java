package com.tms.webshop.controller;

import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageServiceAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.tms.webshop.model.enums.RequestParamsConstants.IMAGE;
import static com.tms.webshop.model.enums.RequestParamsConstants.IMAGE_NAME;
import static com.tms.webshop.model.enums.RequestParamsConstants.NAME;

@Slf4j
@Controller
@RequestMapping(path = "/new-category")
@RequiredArgsConstructor
public class NewCategoryController {
    private final ImageServiceAware imageService;

    private final CategoryServiceAware categoryService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void newCategory(@RequestParam(IMAGE) MultipartFile image, @RequestParam(IMAGE_NAME) String imageName,
                            @RequestParam(NAME) String name) {
        try (InputStream fileStream = image.getInputStream()) {
            imageService.addImage(imageName, fileStream);

            categoryService.addCategory(name, imageName);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }
}
