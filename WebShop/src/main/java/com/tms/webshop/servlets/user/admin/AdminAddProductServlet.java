package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.utilsDB.CategoryDAO;
import com.tms.webshop.utilsDB.ImageDAO;
import com.tms.webshop.utilsDB.ProductDAO;
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

        ImageDAO imageDAO = new ImageDAO();
        imageDAO.addImage(request.getParameter("imageName"), fileStream);

        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(
                request.getParameter("name"),
                request.getParameter("description"),
                new BigDecimal(request.getParameter("price")),
                request.getParameter("imageName"),
                new CategoryDAO().getCategoryId(request.getParameter("category")));
    }
}
