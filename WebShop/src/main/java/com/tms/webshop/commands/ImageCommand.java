package com.tms.webshop.commands;

import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

import static com.tms.webshop.service.ImageServiceAware.CONTEXT_NAME;

@Log4j2
public class ImageCommand implements BaseCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hi");
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
        return null;
    }
}
