package com.ecommerce.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-forgot-otp")
public class VerifyForgotOTPServlet extends HttpServlet {

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
                request.getParameter("otp");

        String generatedOTP =
                (String) session.getAttribute(
                        "forgotOTP"
                );

        if(generatedOTP != null
                && generatedOTP.equals(
                        enteredOTP
                )) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/reset-password.jsp"
            );

        } else {

            response.getWriter().println(
                    "<h2>Invalid OTP</h2>"
            );
        }
    }
}