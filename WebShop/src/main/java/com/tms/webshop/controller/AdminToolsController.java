package com.tms.webshop.controller;

import com.tms.webshop.model.Product;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.ProductServiceAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static com.tms.webshop.model.enums.RequestParamsConstants.CATEGORY;
import static com.tms.webshop.model.enums.RequestParamsConstants.DESCRIPTION;
import static com.tms.webshop.model.enums.RequestParamsConstants.IMAGE;
import static com.tms.webshop.model.enums.RequestParamsConstants.IMAGE_NAME;
import static com.tms.webshop.model.enums.RequestParamsConstants.NAME;
import static com.tms.webshop.model.enums.RequestParamsConstants.PRICE;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminToolsController {
    private final CategoryServiceAware categoryService;
    private final ImageServiceAware imageService;
    private final ProductServiceAware productService;

    @GetMapping
    public ModelAndView showPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.getCategories());
        modelAndView.setViewName(Page.ADMIN.getValue());
        return modelAndView;
    }

    @PostMapping(value = "/new-category")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void createNewCategory(@RequestParam(IMAGE) MultipartFile image, @RequestParam(IMAGE_NAME) String imageName,
                                  @RequestParam(NAME) String name) {
        try (InputStream fileStream = image.getInputStream()) {
            imageService.addImage(imageName, fileStream);

            categoryService.addCategory(name, imageName);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }

    @PostMapping(value = "/new-product")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void execute(@RequestParam(IMAGE) MultipartFile image, @RequestParam(NAME) String name, @RequestParam(IMAGE_NAME) String imageName,
                        @RequestParam(DESCRIPTION) String description, @RequestParam(CATEGORY) String category,
                        @RequestParam(PRICE) String price) {
        try (InputStream fileStream = image.getInputStream()) {
            imageService.addImage(imageName, fileStream);

            Product product = new Product(name, description, new BigDecimal(price), imageName, categoryService.getCategoryId(category));

            productService.addProduct(product);
        } catch (IOException e) {
            log.error("Error, while getting image from request", e);
        }
    }
}
