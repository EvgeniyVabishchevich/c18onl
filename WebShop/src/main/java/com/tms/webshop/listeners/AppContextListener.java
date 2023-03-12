package com.tms.webshop.listeners;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.DBConnectionContainer;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.UserDao;
import com.tms.webshop.dao.database.CategoryDaoDB;
import com.tms.webshop.dao.database.ImageDaoDB;
import com.tms.webshop.dao.database.OrderDaoDB;
import com.tms.webshop.dao.database.ProductDaoDB;
import com.tms.webshop.dao.database.UserDaoDB;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        initConnection(servletContext);
        setDao(servletContext);
    }

    public void initConnection(ServletContext servletContext) {
        String dbUrl = servletContext.getInitParameter("dbUrl");
        String dbUser = servletContext.getInitParameter("dbUser");
        String dbPassword = servletContext.getInitParameter("dbPassword");

        DBConnectionContainer.INSTANCE.setParams(dbUrl, dbUser, dbPassword);
        DBConnectionContainer.INSTANCE.getConnection();
    }

    public void setDao(ServletContext servletContext) {
        servletContext.setAttribute(UserDao.CONTEXT_NAME, new UserDaoDB());
        servletContext.setAttribute(ImageDao.CONTEXT_NAME, new ImageDaoDB());
        servletContext.setAttribute(ProductDao.CONTEXT_NAME, new ProductDaoDB());
        servletContext.setAttribute(CategoryDao.CONTEXT_NAME, new CategoryDaoDB());
        servletContext.setAttribute(OrderDao.CONTEXT_NAME, new OrderDaoDB());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionContainer.INSTANCE.closeConnection();
    }
}
