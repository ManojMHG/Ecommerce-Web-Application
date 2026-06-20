package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;
import com.ecommerce.util.EmailUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter(
                        "email"
                );

        UserDAO userDAO =
                new UserDAO();

        User user =
                userDAO.getUserByEmail(
                        email
                );

        if(user == null) {

            response.getWriter().println(
                    "<h2>Email Not Found</h2>"
            );

            return;
        }

        String otp =
                String.valueOf(
                        (int)(
                                100000
                                + Math.random()
                                * 900000
                        )
                );

        HttpSession session =
                request.getSession();

        session.setAttribute(
                "forgotOTP",
                otp
        );

        session.setAttribute(
                "forgotUser",
                user
        );

        boolean mailSent =
                EmailUtility.sendOTP(
                        email,
                        otp
                );

        if(mailSent) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/forgot-password-otp.jsp"
            );

        } else {

            response.getWriter().println(
                    "<h2>Failed To Send OTP</h2>"
            );
        }
    }
}