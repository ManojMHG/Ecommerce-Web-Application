package com.ecommerce.controller.admin;

import java.io.IOException;

import com.ecommerce.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {

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

        int userId =
                Integer.parseInt(
                        request.getParameter(
                                "userId"
                        )
                );

        UserDAO userDAO =
                new UserDAO();

        boolean status =
                userDAO.deleteUser(
                        userId
                );

        response.sendRedirect(
                request.getContextPath()
                + "/admin/users"
        );
    }
}