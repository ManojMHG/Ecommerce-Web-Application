package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.WishlistDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-wishlist")
public class AddToWishlistServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null
                || session.getAttribute(
                        "loggedInUser"
                ) == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/login.jsp"
            );

            return;
        }

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

        WishlistDAO wishlistDAO =
                new WishlistDAO();

        wishlistDAO.addToWishlist(
                user.getUserId(),
                productId
        );

        response.sendRedirect(
                request.getContextPath()
                + "/products"
        );
    }
}