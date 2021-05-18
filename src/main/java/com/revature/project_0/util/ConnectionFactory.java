package com.revature.project_0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection factory for database queries.  Creates reusable connection to database
 *
 * @author Wezley Singleton
 * @author Everett Diuguid
 */

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory;
    private Properties props = new Properties();

    // Load driver into jvm
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Grab credentials from properties file
    private ConnectionFactory() {
//        try {
////            props.load(new FileReader("../../../../../webapp/WEB-INF/application.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // If no instance of ConnectionFactory exists, create one
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    // create sql connection
    public Connection getConnection() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(
                    System.getenv("host-url"),
                    System.getenv("username"),
                    System.getenv("password"));

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return conn;
    }
}
