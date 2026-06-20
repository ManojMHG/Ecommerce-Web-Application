package com.ecommerce.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-portal")
public class LoginPortalServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String loginType =
                request.getParameter(
                        "loginType"
                );

        if("ADMIN".equals(loginType)) {

            request.getRequestDispatcher(
                    "/admin-login"
            ).forward(
                    request,
                    response
            );

        } else {

            request.getRequestDispatcher(
                    "/login"
            ).forward(
                    request,
                    response
            );
        }
    }
}