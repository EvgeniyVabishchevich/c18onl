package com.tms.webshop.repository.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ConnectionPool {
    private static volatile ConnectionPool INSTANCE;
    private static final String DB_PROPERTY_FILE = "application.properties";
    private static final int MAX_CONNECTION_COUNT = 10;
    private static final int MIN_CONNECTION_COUNT = 5;
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PWD = "db.password";

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private final AtomicInteger currentConnectionNumber = new AtomicInteger(MIN_CONNECTION_COUNT);
    private final BlockingQueue<ConnectionWrapper> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }
        }
        return INSTANCE;
    }

    private ConnectionPool() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            try (InputStream inputStream = classloader.getResourceAsStream(DB_PROPERTY_FILE)) {
                Properties properties = new Properties();
                properties.load(inputStream);

                dbUrl = properties.getProperty(DB_URL);
                dbUser = properties.getProperty(DB_USER);
                dbPassword = properties.getProperty(DB_PWD);

                Class.forName("org.postgresql.Driver");
                for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
                    pool.add(new ConnectionWrapper(this, DriverManager.getConnection(dbUrl, dbUser, dbPassword)));
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("Can't find postgresql driver.", e);
        } catch (FileNotFoundException e) {
            log.error("Error, while trying to load database properties file", e);
        } catch (SQLException e) {
            log.error("Error, while trying to create connection", e);
        } catch (IOException e) {
            log.error("Error, while working with properties inputStream", e);
        }
    }

    public void openAdditionalConnection() {
        try {
            pool.add(new ConnectionWrapper(this, DriverManager.getConnection(dbUrl, dbUser, dbPassword)));
            currentConnectionNumber.addAndGet(1);
        } catch (SQLException e) {
            log.error("Error, while trying to create connection", e);
        }
    }

    public ConnectionWrapper getConnection() throws Exception {
        ConnectionWrapper connectionWrapper;
        try {
            if (pool.isEmpty() && currentConnectionNumber.get() < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();
            }
            connectionWrapper = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Connection limit reached!!!");
        }
        return connectionWrapper;
    }

    public void closeConnection(ConnectionWrapper connectionWrapper) throws Exception {
        if (connectionWrapper != null) {
            if (currentConnectionNumber.get() > MIN_CONNECTION_COUNT) {
                try {
                    connectionWrapper.getConnection().close();
                    currentConnectionNumber.decrementAndGet();
                } catch (SQLException e) {
                    log.error("Error, while trying to close connection.", e);
                }
            } else {
                try {
                    pool.put(connectionWrapper);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new Exception("Connection wasn't returned into pool properly", e);
                }
            }
        }
    }

    public void closeAllConnections() {
        for (ConnectionWrapper connectionWrapper : pool) {
            try {
                connectionWrapper.getConnection().close();
            } catch (SQLException e) {
                log.error("Error, while trying to close all connections", e);
            }
        }
    }
}
