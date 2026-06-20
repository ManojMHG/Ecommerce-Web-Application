package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Review;
import com.ecommerce.util.DBConnection;

public class ReviewDAO {

    public boolean addReview(Review review) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO reviews "
                    + "(product_id, user_id, rating, review_text) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    review.getProductId()
            );

            preparedStatement.setInt(
                    2,
                    review.getUserId()
            );

            preparedStatement.setInt(
                    3,
                    review.getRating()
            );

            preparedStatement.setString(
                    4,
                    review.getReviewText()
            );

            int rowsInserted =
                    preparedStatement.executeUpdate();

            if(rowsInserted > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public List<Review> getReviewsByProductId(
            int productId
    ) {

        List<Review> reviewList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT r.*, u.name "
                    + "FROM reviews r "
                    + "JOIN users u "
                    + "ON r.user_id = u.user_id "
                    + "WHERE r.product_id = ? "
                    + "ORDER BY r.review_date DESC";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    productId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Review review =
                        new Review();

                review.setReviewId(
                        resultSet.getInt("review_id")
                );

                review.setProductId(
                        resultSet.getInt("product_id")
                );

                review.setUserId(
                        resultSet.getInt("user_id")
                );

                review.setRating(
                        resultSet.getInt("rating")
                );

                review.setReviewText(
                        resultSet.getString("review_text")
                );

                review.setReviewDate(
                        resultSet.getTimestamp("review_date")
                );

                review.setUserName(
                        resultSet.getString("name")
                );

                reviewList.add(review);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return reviewList;
    }
    
    public double getAverageRating(
            int productId
    ) {

        double averageRating = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT AVG(rating) "
                    + "FROM reviews "
                    + "WHERE product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    productId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                averageRating =
                        resultSet.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return averageRating;
    }
}