package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import com.ecommerce.util.EmailUtility;
import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProductDAO;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Session Validation

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/login.jsp"
            );

            return;
        }

        // Logged In User

        User user =
                (User) session.getAttribute("loggedInUser");

        int userId =
                user.getUserId();

        // Cart Operations

        CartDAO cartDAO =
                new CartDAO();

        int cartId =
                cartDAO.getCartIdByUserId(userId);

        List<CartItem> cartItemList =
                cartDAO.getCartItemsByCartId(cartId);

        // Calculate Grand Total

        double grandTotal = 0;

        for (CartItem item : cartItemList) {

            grandTotal +=
                    item.getPrice()
                    * item.getQuantity();
        }

        // Final Stock Validation

        ProductDAO productDAO =
                new ProductDAO();

        for (CartItem item : cartItemList) {

            Product product =
                    productDAO.getProductById(
                            item.getProductId()
                    );

            if (item.getQuantity()
                    > product.getStockQuantity()) {

                response.getWriter().println(

                        "<h2>Checkout Failed</h2>"
                        + "<p>Insufficient stock for: "
                        + item.getProductName()
                        + "</p>"
                );

                return;
            }
        }

        // Create Order

        OrderDAO orderDAO =
                new OrderDAO();

        int orderId =
                orderDAO.createOrder(
                        userId,
                        grandTotal,
                        user.getAddress()
                );

        // Insert Order Items

        orderDAO.insertOrderItems(
                orderId,
                cartItemList
        );

        // Update Product Stock

        for (CartItem item : cartItemList) {

            productDAO.updateStock(
                    item.getProductId(),
                    item.getQuantity()
            );
        }
        
        StringBuilder productList =
                new StringBuilder();

        for(CartItem item : cartItemList) {

            productList.append(
                    item.getProductName()
            );

            productList.append(
                    " x "
            );

            productList.append(
                    item.getQuantity()
            );

            productList.append(
                    "\n"
            );
        }
        
        EmailUtility.sendOrderConfirmation(
                user.getEmail(),
                user.getName(),
                orderId,
                productList.toString(),
                grandTotal
        );

        // Clear Cart

        orderDAO.clearCart(cartId);

        // Success Response

        response.getWriter().println(
                "<h2>Order Placed Successfully</h2>"
        );
    }
}