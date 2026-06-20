package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.ReviewDAO;
import com.ecommerce.model.Review;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-review")
public class AddReviewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null
                || session.getAttribute("loggedInUser") == null) {

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

        Review review =
                new Review();

        review.setProductId(
                Integer.parseInt(
                        request.getParameter(
                                "productId"
                        )
                )
        );

        review.setUserId(
                user.getUserId()
        );

        review.setRating(
                Integer.parseInt(
                        request.getParameter(
                                "rating"
                        )
                )
        );

        review.setReviewText(
                request.getParameter(
                        "reviewText"
                )
        );

        ReviewDAO reviewDAO =
                new ReviewDAO();

        reviewDAO.addReview(review);

        response.sendRedirect(
                request.getContextPath()
                + "/products"
        );
    }
}