package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet(value = "/admin/add-category")
public class AdminAddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (InputStream fileStream = request.getPart("image").getInputStream()) {
            ImageService imageService = (ImageService) request.getServletContext().getAttribute(ImageServiceAware.CONTEXT_NAME);
            imageService.addImage(request.getParameter("imageName"), fileStream);

            CategoryService categoryService = (CategoryService) request.getServletContext().getAttribute(CategoryServiceAware.CONTEXT_NAME);
            categoryService.addCategory(request.getParameter("name"), request.getParameter("imageName"));
        }
    }
}
