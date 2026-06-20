package com.ecommerce.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get Existing Session

        HttpSession session = request.getSession(false);

        // Invalidate Session

        if (session != null) {

            session.invalidate();
        }

        // Redirect to Login Page

        response.sendRedirect(
                request.getContextPath()
                + "/jsp/user/login.jsp"
        );
    }
}