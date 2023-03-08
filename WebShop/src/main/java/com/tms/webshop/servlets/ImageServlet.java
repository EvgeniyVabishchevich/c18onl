package com.tms.webshop.servlets;

import com.tms.webshop.dao.ImageDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/images/*")
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageDao imageDao = (ImageDao) request.getServletContext().getAttribute(ImageDao.CONTEXT_NAME);
        String imageName = request.getPathInfo().substring(1);
        byte[] image = imageDao.getImageByName(imageName);

        response.setContentType(getServletContext().getMimeType(imageName));
        response.setContentLength(image.length);
        response.getOutputStream().write(image);
    }
}
