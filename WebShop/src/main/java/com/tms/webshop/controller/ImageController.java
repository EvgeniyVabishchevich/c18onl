package com.tms.webshop.controller;

import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageServiceAware imageService;

    @GetMapping("/{imageName}")
    @ResponseStatus(value = HttpStatus.OK)
    public void getImage(@PathVariable String imageName, HttpServletResponse response, HttpServletRequest request) {
        byte[] image = imageService.getImageByName(imageName);
        response.setContentType(request.getServletContext().getMimeType(imageName));
        response.setContentLength(image.length);

        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            log.error("Error, while trying to write stream in response", e);
        }
    }
}
