package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.database.ProductDaoDb;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@MultipartConfig
@WebServlet(value = "/admin/add-product")
public class AdminAddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream fileStream = request.getPart("image").getInputStream();

        ServletContext servletContext = request.getServletContext();

        ImageDao imageDao = (ImageDao) servletContext.getAttribute(ImageDao.CONTEXT_NAME);
        imageDao.addImage(request.getParameter("imageName"), fileStream);

        CategoryDao categoryDao = (CategoryDao) servletContext.getAttribute(CategoryDao.CONTEXT_NAME);
        ProductDaoDb productDAODB = (ProductDaoDb) servletContext.getAttribute(ProductDao.CONTEXT_NAME);
        productDAODB.addProduct(
                request.getParameter("name"),
                request.getParameter("description"),
                new BigDecimal(request.getParameter("price")),
                request.getParameter("imageName"),
                categoryDao.getCategoryId(request.getParameter("category")));
    }
}
