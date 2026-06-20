package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.model.Admin;
import com.ecommerce.util.DBConnection;

public class AdminDAO {

    public Admin validateAdmin(
            String email,
            String password
    ) {

        Admin admin = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM admins "
                    + "WHERE email = ? "
                    + "AND password = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            preparedStatement.setString(2, password);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                admin = new Admin();

                admin.setAdminId(
                        resultSet.getInt("admin_id")
                );

                admin.setAdminName(
                        resultSet.getString("admin_name")
                );

                admin.setEmail(
                        resultSet.getString("email")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return admin;
    }
}