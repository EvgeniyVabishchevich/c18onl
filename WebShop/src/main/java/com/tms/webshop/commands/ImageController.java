package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.tms.webshop.model.enums.Page.CURRENT;

@Slf4j
public class ImageController implements BaseCommandController {

    @Inject
    private ImageServiceAware imageService;

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String imageName = request.getParameter(RequestParams.IMAGE.getValue());
        byte[] image = imageService.getImageByName(imageName);

        response.setContentType(request.getServletContext().getMimeType(imageName));
        response.setContentLength(image.length);
        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            log.error("Error, while trying to write stream in response", e);
        }
        return CURRENT;
    }

    public void setImageService(ImageServiceAware imageService) {
        this.imageService = imageService;
    }
}
