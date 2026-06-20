package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.CartDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int cartItemId =
                Integer.parseInt(
                        request.getParameter("cartItemId")
                );

        CartDAO cartDAO =
                new CartDAO();

        boolean status =
                cartDAO.removeCartItem(cartItemId);

        response.sendRedirect(
                request.getContextPath()
                + "/cart"
        );
    }
}