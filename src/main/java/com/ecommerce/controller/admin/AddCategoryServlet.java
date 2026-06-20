package com.ecommerce.controller.admin;

import java.io.IOException;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-category")
public class AddCategoryServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
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

        Category category =
                new Category();

        category.setCategoryName(
                request.getParameter(
                        "categoryName"
                )
        );

        category.setDescription(
                request.getParameter(
                        "description"
                )
        );

        CategoryDAO dao =
                new CategoryDAO();

        dao.addCategory(category);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/dashboard"
        );
    }
}