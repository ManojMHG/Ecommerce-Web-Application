package com.ecommerce.util;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {

            System.out.println("Connection Successful");

        } else {

            System.out.println("Connection Failed");
        }
    }
}