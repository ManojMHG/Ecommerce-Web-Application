package com.ecommerce.controller.admin;

import java.io.IOException;

import com.ecommerce.dao.AdminDAO;
import com.ecommerce.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        AdminDAO adminDAO =
                new AdminDAO();

        Admin admin =
                adminDAO.validateAdmin(
                        email,
                        password
                );

        if (admin != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedInAdmin",
                    admin
            );

            response.sendRedirect(
            	    request.getContextPath()
            	    + "/admin/dashboard"
            	);
        } else {

            response.getWriter().println(
                    "<h2>Invalid Admin Credentials</h2>"
            );
        }
    }
}