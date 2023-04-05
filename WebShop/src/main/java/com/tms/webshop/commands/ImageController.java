package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.tms.webshop.service.ImageServiceAware.CONTEXT_NAME;

@Slf4j
public class ImageController implements BaseCommandController {
    @Override
    public Pages execute(HttpServletRequest request, HttpServletResponse response) {
        ImageService imageService = (ImageService) request.getServletContext().getAttribute(CONTEXT_NAME);
        String imageName = request.getParameter(RequestParams.IMAGE.getValue());
        byte[] image = imageService.getImageByName(imageName);

        response.setContentType(request.getServletContext().getMimeType(imageName));
        response.setContentLength(image.length);
        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            log.error("Error, while trying to write stream in response", e);
        }
        log.info(request.getRequestURI());
        return Pages.CURRENT;
    }
}
