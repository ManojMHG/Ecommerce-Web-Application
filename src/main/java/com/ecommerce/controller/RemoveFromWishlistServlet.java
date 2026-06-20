package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.WishlistDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-from-wishlist")
public class RemoveFromWishlistServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int wishlistId =
                Integer.parseInt(
                        request.getParameter(
                                "wishlistId"
                        )
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