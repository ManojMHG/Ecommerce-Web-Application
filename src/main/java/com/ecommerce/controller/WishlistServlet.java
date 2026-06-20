package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.WishlistDAO;
import com.ecommerce.model.User;
import com.ecommerce.model.Wishlist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/wishlist")
public class WishlistServlet
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

        WishlistDAO wishlistDAO =
                new WishlistDAO();

        List<Wishlist> wishlist =
                wishlistDAO.getWishlistByUserId(
                        user.getUserId()
                );

        request.setAttribute(
                "wishlist",
                wishlist
        );

        request.getRequestDispatcher(
                "/jsp/user/wishlist.jsp"
        ).forward(
                request,
                response
        );
    }
}