package com.tms.webshop.listeners;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.UserDao;
import com.tms.webshop.dao.database.CategoryDaoDb;
import com.tms.webshop.dao.database.ConnectionPool;
import com.tms.webshop.dao.database.ImageDaoDb;
import com.tms.webshop.dao.database.OrderDaoDb;
import com.tms.webshop.dao.database.ProductDaoDb;
import com.tms.webshop.dao.database.UserDaoDb;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;

@WebListener
public class AppContextListener implements ServletContextListener {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (Exception e) {
            System.out.println("Error, while trying to get connection.");
        }
        setDao(servletContext);
    }

    public void setDao(ServletContext servletContext) {
        servletContext.setAttribute(UserDao.CONTEXT_NAME, new UserDaoDb(connection));
        servletContext.setAttribute(ImageDao.CONTEXT_NAME, new ImageDaoDb(connection));
        servletContext.setAttribute(ProductDao.CONTEXT_NAME, new ProductDaoDb(connection));
        servletContext.setAttribute(CategoryDao.CONTEXT_NAME, new CategoryDaoDb(connection));
        servletContext.setAttribute(OrderDao.CONTEXT_NAME, new OrderDaoDb(connection));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Error, while trying to close connection.");
        }
    }
}
