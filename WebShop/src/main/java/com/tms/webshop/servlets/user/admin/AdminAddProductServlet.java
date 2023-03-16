package com.tms.webshop.servlets.user.admin;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.database.ProductDaoDb;
import com.tms.webshop.model.Product;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
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

        ImageService imageService = (ImageService) servletContext.getAttribute(ImageServiceAware.CONTEXT_NAME);
        imageService.addImage(request.getParameter("imageName"), fileStream);

        CategoryService categoryService = (CategoryService) servletContext.getAttribute(CategoryServiceAware.CONTEXT_NAME);
        ProductService productService = (ProductService) servletContext.getAttribute(ProductServiceAware.CONTEXT_NAME);

        Product product = new Product(request.getParameter("name"), request.getParameter("description"),
                new BigDecimal(request.getParameter("price")), request.getParameter("imageName"),
                categoryService.getCategoryId(request.getParameter("category")));

        productService.addProduct(product);
    }
}
