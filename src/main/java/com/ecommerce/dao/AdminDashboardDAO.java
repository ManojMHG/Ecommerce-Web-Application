package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.SalesReport;

import com.ecommerce.util.DBConnection;

public class AdminDashboardDAO {

    public int getTotalProducts() {

        int count = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT COUNT(*) FROM products";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                count =
                        resultSet.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    public int getTotalUsers() {

        int count = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT COUNT(*) FROM users";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                count =
                        resultSet.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    public int getTotalOrders() {

        int count = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT COUNT(*) FROM orders";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                count =
                        resultSet.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }

    public double getTotalRevenue() {

        double revenue = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT SUM(total_amount) "
                    + "FROM orders";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                revenue =
                        resultSet.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return revenue;
    }
    
    public double getAverageOrderValue() {

        double average = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT AVG(total_amount) "
                    + "FROM orders";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                average =
                        resultSet.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return average;
    }
    
    public int getDeliveredOrders() {

        int count = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT COUNT(*) "
                    + "FROM orders "
                    + "WHERE order_status = 'Delivered'";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                count =
                        resultSet.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return count;
    }
    
    public List<SalesReport>
    getTopSellingProducts() {

List<SalesReport> reportList =
        new ArrayList<>();

try {

    Connection connection =
            DBConnection.getConnection();

    String query =
            "SELECT p.product_name, "
            + "SUM(oi.quantity) AS total_sold "
            + "FROM order_items oi "
            + "JOIN products p "
            + "ON oi.product_id = p.product_id "
            + "GROUP BY p.product_id "
            + "ORDER BY total_sold DESC "
            + "LIMIT 5";

    PreparedStatement preparedStatement =
            connection.prepareStatement(query);

    ResultSet resultSet =
            preparedStatement.executeQuery();

    while(resultSet.next()) {

        SalesReport report =
                new SalesReport();

        report.setProductName(
                resultSet.getString(
                        "product_name"
                )
        );

        report.setTotalSold(
                resultSet.getInt(
                        "total_sold"
                )
        );

        reportList.add(report);
    }

} catch(Exception e) {

    e.printStackTrace();
}

return reportList;
}
}