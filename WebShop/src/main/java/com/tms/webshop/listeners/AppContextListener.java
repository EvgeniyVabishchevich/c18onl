package com.tms.webshop.listeners;

import com.tms.webshop.dao.DBConnectionContainer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String dbUrl = servletContext.getInitParameter("dbUrl");
        String dbUser = servletContext.getInitParameter("dbUser");
        String dbPassword = servletContext.getInitParameter("dbPassword");

        DBConnectionContainer.INSTANCE.setParams(dbUrl, dbUser, dbPassword);
        DBConnectionContainer.INSTANCE.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionContainer.INSTANCE.closeConnection();
    }
}
