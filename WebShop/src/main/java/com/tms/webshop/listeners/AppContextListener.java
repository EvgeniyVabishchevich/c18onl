package com.tms.webshop.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

import static com.tms.webshop.repository.BaseRepository.CONNECTION_POOL;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ThreadContext.remove("conversationId");
        CONNECTION_POOL.closeAllConnections();
    }
}
