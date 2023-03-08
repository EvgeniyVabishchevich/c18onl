package com.tms.webshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnectionContainer {
    INSTANCE;

    private Connection connection;
    private String dbUrl, dbUser, dbPassword;

    public void setParams(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    private void createConnection(){
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
        if (connection == null){
            createConnection();
        }
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
