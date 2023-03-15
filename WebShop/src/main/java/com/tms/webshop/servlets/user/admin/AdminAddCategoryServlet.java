package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
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
        InputStream fileStream = request.getPart("image").getInputStream();

        ImageDao imageDao = (ImageDao) request.getServletContext().getAttribute(ImageDao.CONTEXT_NAME);
        imageDao.addImage(request.getParameter("imageName"), fileStream);

        CategoryDao categoryDao = (CategoryDao) request.getServletContext().getAttribute(CategoryDao.CONTEXT_NAME);
        categoryDao.addCategory(request.getParameter("name"), request.getParameter("imageName"));
    }
}
