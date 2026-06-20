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

@WebServlet("/change-password")
public class ChangePasswordServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        String currentPassword =
                request.getParameter(
                        "currentPassword"
                );

        String newPassword =
                request.getParameter(
                        "newPassword"
                );

        String confirmPassword =
                request.getParameter(
                        "confirmPassword"
                );

        if(!user.getPassword().equals(
                currentPassword)) {

            response.getWriter().println(
                    "<h3>Current Password Incorrect</h3>"
            );

            return;
        }

        if(!newPassword.equals(
                confirmPassword)) {

            response.getWriter().println(
                    "<h3>Passwords Do Not Match</h3>"
            );

            return;
        }

        UserDAO dao =
                new UserDAO();

        dao.updatePassword(
                user.getUserId(),
                newPassword
        );

        user.setPassword(
                newPassword
        );

        session.setAttribute(
                "loggedInUser",
                user
        );

        response.getWriter().println(
                "<h3>Password Updated Successfully</h3>"
        );
    }
}