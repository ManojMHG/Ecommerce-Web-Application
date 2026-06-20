package com.ecommerce.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.ecommerce.model.OrderItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import com.ecommerce.model.Order;

import com.ecommerce.model.CartItem;
import com.ecommerce.util.DBConnection;

public class OrderDAO {

    // Create Order

	public int createOrder(
	        int userId,
	        double totalAmount,
	        String shippingAddress
	) {

        int orderId = 0;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO orders(user_id, total_amount, order_status, shipping_address) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            preparedStatement.setInt(1, userId);

            preparedStatement.setDouble(2, totalAmount);

            preparedStatement.setString(3, "PLACED");
            
            preparedStatement.setString(4, shippingAddress);

            preparedStatement.executeUpdate();

            ResultSet resultSet =
                    preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                orderId = resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderId;
    }

    // Insert Order Items

    public void insertOrderItems(
            int orderId,
            List<CartItem> cartItemList
    ) {

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO order_items(order_id, product_id, quantity, price) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            for (CartItem item : cartItemList) {

                preparedStatement.setInt(
                        1,
                        orderId
                );

                preparedStatement.setInt(
                        2,
                        item.getProductId()
                );

                preparedStatement.setInt(
                        3,
                        item.getQuantity()
                );

                preparedStatement.setDouble(
                        4,
                        item.getPrice()
                );

                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // Clear Cart

    public void clearCart(int cartId) {

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM cart_items WHERE cart_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public List<OrderItem> getOrderItemsByUserId(
            int userId
    ) {

        List<OrderItem> orderItemList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT oi.*, "
                    + "o.order_status, "
                    + "p.product_name, "
                    + "p.image_url "
                    + "FROM order_items oi "
                    + "JOIN orders o "
                    + "ON oi.order_id = o.order_id "
                    + "JOIN products p "
                    + "ON oi.product_id = p.product_id "
                    + "WHERE o.user_id = ? "
                    + "ORDER BY o.order_date DESC";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderItemId(
                        resultSet.getInt("order_item_id")
                );

                orderItem.setOrderId(
                        resultSet.getInt("order_id")
                );
                
                orderItem.setOrderStatus(
                        resultSet.getString("order_status")
                );

                orderItem.setProductId(
                        resultSet.getInt("product_id")
                );

                orderItem.setQuantity(
                        resultSet.getInt("quantity")
                );

                orderItem.setPrice(
                        resultSet.getDouble("price")
                );

                orderItem.setProductName(
                        resultSet.getString("product_name")
                );

                orderItem.setImageUrl(
                        resultSet.getString("image_url")
                );
                
                

                orderItemList.add(orderItem);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderItemList;
    }
    
    public List<Order> getAllOrders() {

        List<Order> orderList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT o.*, "
                    + "u.name, "
                    + "u.email "
                    + "FROM orders o "
                    + "JOIN users u "
                    + "ON o.user_id = u.user_id "
                    + "ORDER BY o.order_date DESC";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order =
                        new Order();

                order.setOrderId(
                        resultSet.getInt("order_id")
                );

                order.setUserId(
                        resultSet.getInt("user_id")
                );

                order.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                order.setOrderStatus(
                        resultSet.getString("order_status")
                );

                order.setOrderDate(
                        resultSet.getTimestamp("order_date")
                );

                order.setUserName(
                        resultSet.getString("name")
                );

                order.setEmail(
                        resultSet.getString("email")
                );

                orderList.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderList;
    }
    
    public boolean updateOrderStatus(
            int orderId,
            String orderStatus
    ) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE orders "
                    + "SET order_status = ? "
                    + "WHERE order_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    orderStatus
            );

            preparedStatement.setInt(
                    2,
                    orderId
            );

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
    
    public Order getOrderById(
            int orderId
    ) {

        Order order = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT o.*, "
                    + "u.name, "
                    + "u.email "
                    + "FROM orders o "
                    + "JOIN users u "
                    + "ON o.user_id = u.user_id "
                    + "WHERE o.order_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setInt(
                    1,
                    orderId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                order = new Order();

                order.setOrderId(
                        resultSet.getInt("order_id")
                );

                order.setUserId(
                        resultSet.getInt("user_id")
                );

                order.setUserName(
                        resultSet.getString("name")
                );

                order.setEmail(
                        resultSet.getString("email")
                );

                order.setOrderStatus(
                        resultSet.getString("order_status")
                );

                order.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                order.setOrderDate(
                        resultSet.getTimestamp("order_date")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return order;
    }
    
    public List<OrderItem> getOrderItemsByOrderId(
            int orderId
    ) {

        List<OrderItem> orderItemList =
                new ArrayList<>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT oi.*, "
                    + "p.product_name "
                    + "FROM order_items oi "
                    + "JOIN products p "
                    + "ON oi.product_id = p.product_id "
                    + "WHERE oi.order_id = ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query
                    );

            preparedStatement.setInt(
                    1,
                    orderId
            );

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                OrderItem item =
                        new OrderItem();

                item.setOrderItemId(
                        resultSet.getInt(
                                "order_item_id"
                        )
                );

                item.setOrderId(
                        resultSet.getInt(
                                "order_id"
                        )
                );

                item.setProductId(
                        resultSet.getInt(
                                "product_id"
                        )
                );

                item.setQuantity(
                        resultSet.getInt(
                                "quantity"
                        )
                );

                item.setPrice(
                        resultSet.getDouble(
                                "price"
                        )
                );

                item.setProductName(
                        resultSet.getString(
                                "product_name"
                        )
                );

                orderItemList.add(
                        item
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orderItemList;
    }
}