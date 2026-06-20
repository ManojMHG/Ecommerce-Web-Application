package com.ecommerce.controller.admin;

import java.io.IOException;

import com.ecommerce.dao.CategoryDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null
                || session.getAttribute("loggedInAdmin") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/admin/admin-login.jsp"
            );

            return;
        }

        int categoryId =
                Integer.parseInt(
                        request.getParameter(
                                "categoryId"
                        )
                );

        CategoryDAO dao =
                new CategoryDAO();

        dao.deleteCategory(categoryId);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/categories"
        );
    }
}