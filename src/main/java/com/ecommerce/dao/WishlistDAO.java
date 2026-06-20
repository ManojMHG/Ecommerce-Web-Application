package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Wishlist;
import com.ecommerce.util.DBConnection;

public class WishlistDAO {

	public boolean addToWishlist(
	        int userId,
	        int productId
	) {

	    boolean status = false;

	    try {

	        Connection connection =
	                DBConnection.getConnection();

	        String checkQuery =
	                "SELECT * FROM wishlist "
	                + "WHERE user_id = ? "
	                + "AND product_id = ?";

	        PreparedStatement checkStatement =
	                connection.prepareStatement(
	                        checkQuery
	                );

	        checkStatement.setInt(
	                1,
	                userId
	        );

	        checkStatement.setInt(
	                2,
	                productId
	        );

	        ResultSet resultSet =
	                checkStatement.executeQuery();

	        if(resultSet.next()) {

	            return false;
	        }

	        String insertQuery =
	                "INSERT INTO wishlist(user_id, product_id) "
	                + "VALUES (?, ?)";

	        PreparedStatement insertStatement =
	                connection.prepareStatement(
	                        insertQuery
	                );

	        insertStatement.setInt(
	                1,
	                userId
	        );

	        insertStatement.setInt(
	                2,
	                productId
	        );

	        status =
	                insertStatement.executeUpdate() > 0;

	    } catch(Exception e) {

	        e.printStackTrace();
	    }

	    return status;
	}

    public List<Wishlist> getWishlistByUserId(
            int userId
    ) {

        List<Wishlist> wishlist =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT w.*, "
                    + "p.product_name, "
                    + "p.image_url, "
                    + "p.price "
                    + "FROM wishlist w "
                    + "JOIN products p "
                    + "ON w.product_id = p.product_id "
                    + "WHERE w.user_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    userId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Wishlist item =
                        new Wishlist();

                item.setWishlistId(
                        resultSet.getInt(
                                "wishlist_id"
                        )
                );

                item.setProductId(
                        resultSet.getInt(
                                "product_id"
                        )
                );

                item.setProductName(
                        resultSet.getString(
                                "product_name"
                        )
                );

                item.setImageUrl(
                        resultSet.getString(
                                "image_url"
                        )
                );

                item.setPrice(
                        resultSet.getDouble(
                                "price"
                        )
                );

                wishlist.add(item);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return wishlist;
    }
    
    
    public boolean removeFromWishlist(
            int wishlistId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM wishlist "
                    + "WHERE wishlist_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    wishlistId
            );

            status =
                    preparedStatement.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}