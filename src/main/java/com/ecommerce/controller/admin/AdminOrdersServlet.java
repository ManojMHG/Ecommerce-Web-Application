package com.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders")
public class AdminOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderList =
                orderDAO.getAllOrders();
        
        System.out.println("Orders found: " + orderList.size());

        request.setAttribute(
                "orderList",
                orderList
        );

        request.getRequestDispatcher(
                "/jsp/admin/admin-orders.jsp"
        ).forward(request, response);
    }
}