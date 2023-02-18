package com.tms.utilsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnectionContainer {
    INSTANCE;

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webshop",
                        "postgres", "1234qwer");
            } catch (SQLException e) {
                System.out.println("Connection error");
            } catch (ClassNotFoundException e) {
                System.out.println("Can't find postgresql driver.");
            }
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
