package com.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/users")
public class ViewUsersServlet extends HttpServlet {

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
                        "loggedInAdmin"
                ) == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/admin/admin-login.jsp"
            );

            return;
        }

        UserDAO userDAO =
                new UserDAO();

        List<User> userList =
                userDAO.getAllUsers();

        request.setAttribute(
                "userList",
                userList
        );

        request.getRequestDispatcher(
                "/jsp/admin/users.jsp"
        ).forward(request, response);
    }
}