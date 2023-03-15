package com.tms.webshop.dao.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

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
            InputStream input = classloader.getResourceAsStream(DB_PROPERTY_FILE);
            Properties properties = new Properties();
            properties.load(input);
            input.close();

            dbUrl = properties.getProperty(DB_URL);
            dbUser = properties.getProperty(DB_USER);
            dbPassword = properties.getProperty(DB_PWD);

            Class.forName("org.postgresql.Driver");
            for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
                pool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find postgresql driver.");
        } catch (FileNotFoundException e) {
            System.out.println("Error, while trying to load database properties file");
        } catch (SQLException e) {
            System.out.println("Error, while trying to create connection");
        } catch (IOException e) {
            System.out.println("Error, while working with properties inputStream");
        }
    }

    public void openAdditionalConnection() {
        try {
            pool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
            currentConnectionNumber.addAndGet(1);
        } catch (SQLException e) {
            System.out.println("Error, while trying to create connection");
        }
    }

    public Connection getConnection() throws Exception {
        Connection connection;
        try {
            if (pool.isEmpty() && currentConnectionNumber.get() < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();
            }
            connection = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Connection limit reached!!!");
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            if (currentConnectionNumber.get() > MIN_CONNECTION_COUNT) {
                try {
                    connection.close();
                    currentConnectionNumber.decrementAndGet();
                } catch (SQLException e) {
                    System.out.println("Error, while trying to close connection.");
                }
            }
            else {
                try {
                    pool.put(connection);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new Exception("Connection wasn't returned into pool properly");
                }
            }
        }
    }
}
