package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ecommerce_db";

    private static final String USERNAME = "";

    private static final String PASSWORD = "";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return connection;
    }
}
