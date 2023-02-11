package com.example.webshop;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.webshop.model.Category;
import com.example.webshop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/categories")
public class ProductsListSevlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("categories", createCategories());
        try {
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        } catch (ServletException e) {
            System.out.println("Error while forward to products.jsp ");
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    public List<Category> createCategories(){
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("Cameras", "camera.jpg"));

        List<Product> phoneProducts = new ArrayList<>();
        phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500), "iphone11.png"));
        phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(500), "iphone14.jpeg"));
        categories.add(new Category("Phones", "phones.jpg", phoneProducts));

        List<Product> fridgesProducts = new ArrayList<>();
        //phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500)));
        //phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(500)));
        categories.add(new Category("Fridges", "fridge.jpg", fridgesProducts));

        List<Product> navigatorsProducts = new ArrayList<>();
        //phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500)));
        //phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(500)));
        categories.add(new Category("Gps-navigators", "gps-navigator.jpg", navigatorsProducts));

        List<Product> laptopsProducts = new ArrayList<>();
        //phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500)));
        //phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(500)));
        categories.add(new Category("Laptops", "laptops.jpg", laptopsProducts));

        List<Product> carsProducts = new ArrayList<>();
        //phoneProducts.add(new Product("Iphone 11", "Very nice phone.", new BigDecimal(500)));
        //phoneProducts.add(new Product("Iphone 14", "Same as iphone 11, but more expensive.", new BigDecimal(500)));
        categories.add(new Category("Cars", "car.jpg", carsProducts));

        return categories;
    }
}