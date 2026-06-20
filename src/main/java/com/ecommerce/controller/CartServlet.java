package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

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

        CartDAO cartDAO = new CartDAO();

        int cartId =
                cartDAO.getCartIdByUserId(
                        user.getUserId()
                );

        List<CartItem> cartItemList =
                cartDAO.getCartItemsByCartId(cartId);

        request.setAttribute(
                "cartItemList",
                cartItemList
        );

        request.getRequestDispatcher(
                "/jsp/user/cart.jsp"
        ).forward(request, response);
    }
}