package com.tms.webshop.utilsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnectionContainer {
    INSTANCE;

    private Connection connection;

    public void createConnection(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.out.println("Connection error");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find postgresql driver.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception, when trying to close connection.");
        }
    }
}
