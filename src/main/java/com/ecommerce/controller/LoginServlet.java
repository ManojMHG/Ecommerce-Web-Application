package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get Form Data

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        // Validate User

        UserDAO userDAO = new UserDAO();

        User user = userDAO.validateUser(email, password);

        if (user != null) {

            HttpSession session =
                    request.getSession();

            if(user.isOtpEnabled()) {

                String otp =
                        String.valueOf(
                                (int)(
                                        100000
                                        + Math.random()
                                        * 900000
                                )
                        );

                session.setAttribute(
                        "generatedOTP",
                        otp
                );

                session.setAttribute(
                        "otpUser",
                        user
                );

                boolean mailSent =
                        com.ecommerce.util.EmailUtility.sendOTP(
                                user.getEmail(),
                                otp
                        );

                if(mailSent) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/jsp/user/otp-verification.jsp"
                    );

                } else {

                    response.getWriter().println(
                            "<h2>Failed To Send OTP</h2>"
                    );
                }

            } else {

                session.setAttribute(
                        "loggedInUser",
                        user
                );

                if(user.getRole().equals(
                        "ADMIN"
                )) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/jsp/admin/dashboard.jsp"
                    );

                } else {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/products"
                    );
                }
            }

        }else {

            response.getWriter().println(
                    "<h2>Invalid Email or Password</h2>"
            );
        }
    }
}