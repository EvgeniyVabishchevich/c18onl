package com.tms.webshop;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tms.webshop.model.Category;
import com.tms.webshop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/categories")
public class CategoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("categories", createCategories());
        try {
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (ServletException e) {
            System.out.println("Error while forward to products.jsp ");
        }
    }

    public void destroy() {
    }

    public List<Category> createCategories(){
        List<Category> categories = new ArrayList<>();

        List<Product> cameraProducts = new ArrayList<>();
        cameraProducts.add(new Product("Camera ultra", "Very nice camera.", new BigDecimal(500), "camera1.jpg"));
        cameraProducts.add(new Product("Camera mega", "Super nice phone.", new BigDecimal(1500), "camera2.jpg"));
        categories.add(new Category("Cameras", "camera.jpg", cameraProducts));

        List<Product> phoneProducts = new ArrayList<>();
        phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500), "iphone11.png"));
        phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(1500), "iphone14.jpeg"));
        categories.add(new Category("Phones", "phones.jpg", phoneProducts));

        List<Product> fridgesProducts = new ArrayList<>();
        fridgesProducts.add(new Product("Fridge x200", "Very nice fridge.", new BigDecimal(500), "fridge1.jpg"));
        fridgesProducts.add(new Product("Fridge x1000", "Better then x200.", new BigDecimal(1500), "fridge2.jpg"));
        categories.add(new Category("Fridges", "fridge.jpg", fridgesProducts));

        List<Product> navigatorsProducts = new ArrayList<>();
        navigatorsProducts.add(new Product("Gps-navigator ultra", "Very nice navigator.", new BigDecimal(500), "navigator1.jpg"));
        navigatorsProducts.add(new Product("Gps-navigator max", "Also very nice.", new BigDecimal(1500), "navigator2.jpg"));
        categories.add(new Category("Gps-navigators", "gps-navigator.jpg", navigatorsProducts));

        List<Product> laptopsProducts = new ArrayList<>();
        laptopsProducts.add(new Product("MSI GP76 Leopard", "Gaming laptop.", new BigDecimal(500), "laptop1.jpg"));
        laptopsProducts.add(new Product("Lenovo z500", "Casual laptop.", new BigDecimal(1500), "laptop2.jpg"));
        categories.add(new Category("Laptops", "laptops.jpg", laptopsProducts));

        List<Product> carsProducts = new ArrayList<>();
        carsProducts.add(new Product("Volvo", "Very nice car.", new BigDecimal(500), "car1.jpg"));
        carsProducts.add(new Product("Audi", "Very very nice car.", new BigDecimal(1500), "car2.png"));
        categories.add(new Category("Cars", "car.jpg", carsProducts));

        return categories;
    }
}