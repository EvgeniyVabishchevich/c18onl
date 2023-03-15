package com.tms.webshop.listeners;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.UserDao;
import com.tms.webshop.dao.database.CategoryDaoDb;
import com.tms.webshop.dao.database.ImageDaoDb;
import com.tms.webshop.dao.database.OrderDaoDb;
import com.tms.webshop.dao.database.ProductDaoDb;
import com.tms.webshop.dao.database.UserDaoDb;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        setDao(servletContext);
    }

    public void setDao(ServletContext servletContext) {
        servletContext.setAttribute(UserDao.CONTEXT_NAME, new UserDaoDb());
        servletContext.setAttribute(ImageDao.CONTEXT_NAME, new ImageDaoDb());
        servletContext.setAttribute(ProductDao.CONTEXT_NAME, new ProductDaoDb());
        servletContext.setAttribute(CategoryDao.CONTEXT_NAME, new CategoryDaoDb());
        servletContext.setAttribute(OrderDao.CONTEXT_NAME, new OrderDaoDb());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
