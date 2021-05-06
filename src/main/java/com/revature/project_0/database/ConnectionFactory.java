package com.revature.project_0.database;

import java.sql.Connection;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        return null;
    }
}
