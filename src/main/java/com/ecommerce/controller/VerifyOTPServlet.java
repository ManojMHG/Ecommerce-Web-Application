package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-otp")
public class VerifyOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/login.jsp"
            );

            return;
        }

        String enteredOTP =
                request.getParameter(
                        "otp"
                );

        String generatedOTP =
                (String) session.getAttribute(
                        "generatedOTP"
                );

        User user =
                (User) session.getAttribute(
                        "otpUser"
                );

        if(generatedOTP != null
                && generatedOTP.equals(
                        enteredOTP
                )) {

            session.setAttribute(
                    "loggedInUser",
                    user
            );

            session.removeAttribute(
                    "generatedOTP"
            );

            session.removeAttribute(
                    "otpUser"
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

        } else {

            response.getWriter().println(
                    "<h2>Invalid OTP</h2>"
            );
        }
    }
}