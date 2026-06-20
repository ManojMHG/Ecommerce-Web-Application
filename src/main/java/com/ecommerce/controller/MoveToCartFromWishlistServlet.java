package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.WishlistDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

@WebServlet("/wishlist-to-cart")
public class MoveToCartFromWishlistServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        int productId =
                Integer.parseInt(
                        request.getParameter(
                                "productId"
                        )
                );

        int wishlistId =
                Integer.parseInt(
                        request.getParameter(
                                "wishlistId"
                        )
                );

        CartDAO cartDAO =
                new CartDAO();

        int cartId =
                cartDAO.getCartIdByUserId(
                        user.getUserId()
                );

        cartDAO.addToCart(
                cartId,
                productId,
                1
        );

        WishlistDAO wishlistDAO =
                new WishlistDAO();

        wishlistDAO.removeFromWishlist(
                wishlistId
        );

        response.sendRedirect(
                request.getContextPath()
                + "/wishlist"
        );
    }
}