package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.ProductDao;
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
@WebServlet(name = "AdminAddProductServlet", value = "/admin/add-product")
public class AdminAddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream fileStream = request.getPart("image").getInputStream();

        ImageDao imageDAO = new ImageDao();
        imageDAO.addImage(request.getParameter("imageName"), fileStream);

        ProductDao productDAO = new ProductDao();
        productDAO.addProduct(
                request.getParameter("name"),
                request.getParameter("description"),
                new BigDecimal(request.getParameter("price")),
                request.getParameter("imageName"),
                new CategoryDao().getCategoryId(request.getParameter("category")));
    }
}
