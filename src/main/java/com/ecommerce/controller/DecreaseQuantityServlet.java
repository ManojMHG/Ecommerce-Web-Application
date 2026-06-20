package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/decrease-quantity")
public class DecreaseQuantityServlet extends HttpServlet {

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

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        CartDAO cartDAO =
                new CartDAO();

        int cartId =
                cartDAO.getCartIdByUserId(
                        user.getUserId()
                );

        int quantity =
                cartDAO.getQuantity(
                        cartId,
                        productId
                );

        if (quantity > 1) {

            cartDAO.decreaseQuantity(
                    cartId,
                    productId
            );

        } else {

            cartDAO.removeCartItemByProduct(
                    cartId,
                    productId
            );
        }

        response.sendRedirect(
                request.getContextPath()
                + "/cart"
        );
    }
}