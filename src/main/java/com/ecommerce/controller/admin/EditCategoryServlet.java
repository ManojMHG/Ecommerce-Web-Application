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

@WebServlet("/edit-category")
public class EditCategoryServlet extends HttpServlet {

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

        Category category =
                dao.getCategoryById(
                        categoryId
                );

        request.setAttribute(
                "category",
                category
        );

        request.getRequestDispatcher(
                "/jsp/admin/edit-category.jsp"
        ).forward(request, response);
    }
}