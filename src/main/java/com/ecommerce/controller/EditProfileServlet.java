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

@WebServlet("/update-profile")
public class EditProfileServlet extends HttpServlet {

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

        user.setName(
                request.getParameter(
                        "name"
                )
        );
        
        user.setEmail(
                request.getParameter(
                        "email"
                )
        );

        user.setPhone(
                request.getParameter(
                        "phone"
                )
        );
        
        boolean otpEnabled =
                request.getParameter(
                        "otpEnabled"
                ) != null;

        user.setOtpEnabled(
                otpEnabled
        );

        user.setAddress(
                request.getParameter(
                        "address"
                )
        );
        
        
        
        

        UserDAO dao =
                new UserDAO();

        dao.updateProfile(user);

        session.setAttribute(
                "loggedInUser",
                user
        );

        response.sendRedirect(
                request.getContextPath()
                + "/profile"
        );
    }
}