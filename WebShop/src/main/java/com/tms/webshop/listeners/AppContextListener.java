package com.tms.webshop.listeners;

import com.tms.webshop.dao.database.CategoryDaoDb;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

import static com.tms.webshop.dao.BaseRepository.CONNECTION_POOL;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Application started");

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
        ThreadContext.remove("conversationId");
        CONNECTION_POOL.closeAllConnections();
    }
}
