package com.tms.webshop.servlets;

import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/images/*")
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageService imageService = (ImageService) request.getServletContext().getAttribute(ImageServiceAware.CONTEXT_NAME);
        String imageName = request.getPathInfo().substring(1);
        byte[] image = imageService.getImageByName(imageName);

        response.setContentType(getServletContext().getMimeType(imageName));
        response.setContentLength(image.length);
        response.getOutputStream().write(image);
    }
}
