package com.tms.webshop.listeners;

import com.tms.webshop.dao.database.CategoryDaoDb;
import com.tms.webshop.dao.database.ConnectionPool;
import com.tms.webshop.dao.database.ImageDaoDb;
import com.tms.webshop.dao.database.OrderDaoDb;
import com.tms.webshop.dao.database.ProductDaoDb;
import com.tms.webshop.dao.database.UserDaoDb;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.OrderService;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
import com.tms.webshop.service.UserService;
import com.tms.webshop.service.UserServiceAware;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        setServices(servletContext);
    }

    public void setServices(ServletContext servletContext) {
        servletContext.setAttribute(UserServiceAware.CONTEXT_NAME, new UserService(new UserDaoDb()));
        servletContext.setAttribute(ImageServiceAware.CONTEXT_NAME, new ImageService(new ImageDaoDb()));
        servletContext.setAttribute(ProductServiceAware.CONTEXT_NAME, new ProductService(new ProductDaoDb()));
        servletContext.setAttribute(CategoryServiceAware.CONTEXT_NAME, new CategoryService(new CategoryDaoDb()));
        servletContext.setAttribute(OrderServiceAware.CONTEXT_NAME, new OrderService(new OrderDaoDb()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeAllConnections();
    }
}
