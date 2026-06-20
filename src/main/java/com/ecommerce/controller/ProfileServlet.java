package com.ecommerce.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

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

        request.getRequestDispatcher(
                "/jsp/user/profile.jsp"
        ).forward(request, response);
    }
}