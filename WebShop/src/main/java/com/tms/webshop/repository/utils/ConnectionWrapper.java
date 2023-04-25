package com.tms.webshop.repository.utils;

import java.sql.Connection;

public class ConnectionWrapper implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;

    public ConnectionWrapper(ConnectionPool connectionPool, Connection connection) {
        this.connectionPool = connectionPool;
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connectionPool.closeConnection(this);
    }
}
