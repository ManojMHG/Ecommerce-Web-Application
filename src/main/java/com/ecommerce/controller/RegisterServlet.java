package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get Form Data

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String phone = request.getParameter("phone");

        String address = request.getParameter("address");

        // Create User Object

        User user = new User();

        user.setName(name);

        user.setEmail(email);

        user.setPassword(password);

        user.setPhone(phone);

        user.setAddress(address);

        user.setRole("USER");

        // Call DAO

        UserDAO userDAO = new UserDAO();

        boolean status = userDAO.registerUser(user);

        // Response

        if (status) {

            response.getWriter().println(
                    "<h2>User Registered Successfully</h2>"
            );

        } else {

            response.getWriter().println(
                    "<h2>Registration Failed</h2>"
            );
        }
    }
}