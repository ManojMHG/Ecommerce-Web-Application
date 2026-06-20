package com.ecommerce.controller.admin;

import java.io.IOException;
import com.ecommerce.model.Order;
import com.ecommerce.util.EmailUtility;
import com.ecommerce.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/update-order-status")
public class UpdateOrderStatusServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter("orderId")
                );

        String orderStatus =
                request.getParameter("orderStatus");

        OrderDAO orderDAO =
                new OrderDAO();

        boolean status =
                orderDAO.updateOrderStatus(
                        orderId,
                        orderStatus
                );

        if(status
                && "DELIVERED".equalsIgnoreCase(
                        orderStatus
                )) {

            Order order =
                    orderDAO.getOrderById(
                            orderId
                    );

            if(order != null) {

                EmailUtility.sendDeliveryNotification(
                        order.getEmail(),
                        order.getUserName(),
                        orderId
                );
            }
        }

        response.sendRedirect(
                request.getContextPath()
                + "/admin/orders"
        );
    }
}