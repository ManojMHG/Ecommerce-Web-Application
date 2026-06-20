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

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {

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

        User user =
                (User) session.getAttribute(
                        "forgotUser"
                );

        String newPassword =
                request.getParameter(
                        "password"
                );

        UserDAO userDAO =
                new UserDAO();

        boolean status =
                userDAO.updatePassword(
                        user.getUserId(),
                        newPassword
                );

        if(status) {

            session.removeAttribute(
                    "forgotOTP"
            );

            session.removeAttribute(
                    "forgotUser"
            );

            response.getWriter().println(
                    "<h2>Password Updated Successfully</h2>"
            );

        } else {

            response.getWriter().println(
                    "<h2>Failed To Update Password</h2>"
            );
        }
    }
}