package com.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.util.DBConnection;

public class CartDAO {

    // Get Existing Cart ID

    public int getCartIdByUserId(int userId) {

        int cartId = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT cart_id FROM cart WHERE user_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                cartId = resultSet.getInt("cart_id");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return cartId;
    }
    
    public boolean productExistsInCart(
            int cartId,
            int productId
    ) {

        boolean exists = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM cart_items "
                    + "WHERE cart_id = ? "
                    + "AND product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                exists = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return exists;
    }
    
    public boolean increaseQuantity(
            int cartId,
            int productId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE cart_items "
                    + "SET quantity = quantity + 1 "
                    + "WHERE cart_id = ? "
                    + "AND product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            int rowsUpdated =
                    preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean decreaseQuantity(
            int cartId,
            int productId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE cart_items "
                    + "SET quantity = quantity - 1 "
                    + "WHERE cart_id = ? "
                    + "AND product_id = ? "
                    + "AND quantity > 1";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            int rowsUpdated =
                    preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public int getQuantity(
            int cartId,
            int productId
    ) {

        int quantity = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT quantity "
                    + "FROM cart_items "
                    + "WHERE cart_id = ? "
                    + "AND product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                quantity =
                        resultSet.getInt("quantity");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return quantity;
    }
    
    public boolean removeCartItemByProduct(
            int cartId,
            int productId
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM cart_items "
                    + "WHERE cart_id = ? "
                    + "AND product_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            int rowsDeleted =
                    preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean removeCartItem(int cartItemId) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM cart_items WHERE cart_item_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            int rowsDeleted =
                    preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // Create New Cart

    public int createCart(int userId) {

        int cartId = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO cart(user_id) VALUES(?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

            ResultSet resultSet =
                    preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                cartId = resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return cartId;
    }

    // Add Product To Cart

    public boolean addToCart(
            int cartId,
            int productId,
            int quantity
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO cart_items(cart_id, product_id, quantity) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            preparedStatement.setInt(3, quantity);

            int rowsInserted =
                    preparedStatement.executeUpdate();

            if (rowsInserted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> cartItemList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT ci.*, p.product_name, p.price, p.image_url "
                    + "FROM cart_items ci "
                    + "JOIN products p "
                    + "ON ci.product_id = p.product_id "
                    + "WHERE ci.cart_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem =
                        new CartItem();

                cartItem.setCartItemId(
                        resultSet.getInt("cart_item_id")
                );

                cartItem.setCartId(
                        resultSet.getInt("cart_id")
                );

                cartItem.setProductId(
                        resultSet.getInt("product_id")
                );

                cartItem.setQuantity(
                        resultSet.getInt("quantity")
                );

                cartItem.setProductName(
                        resultSet.getString("product_name")
                );

                cartItem.setPrice(
                        resultSet.getDouble("price")
                );

                cartItem.setImageUrl(
                        resultSet.getString("image_url")
                );

                cartItemList.add(cartItem);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return cartItemList;
    }
}