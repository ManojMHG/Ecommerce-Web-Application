package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

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

        User user =
                (User) session.getAttribute("loggedInUser");

        OrderDAO orderDAO =
                new OrderDAO();

        List<OrderItem> orderItemList =
                orderDAO.getOrderItemsByUserId(
                        user.getUserId()
                );

        request.setAttribute(
                "orderItemList",
                orderItemList
        );

        request.getRequestDispatcher(
                "/jsp/user/orders.jsp"
        ).forward(request, response);
    }
}