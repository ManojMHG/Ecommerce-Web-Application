package com.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-product-form")
public class AddProductFormServlet extends HttpServlet {

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

        CategoryDAO dao =
                new CategoryDAO();

        List<Category> categoryList =
                dao.getAllCategories();

        request.setAttribute(
                "categoryList",
                categoryList
        );

        request.getRequestDispatcher(
                "/jsp/admin/add-product.jsp"
        ).forward(request, response);
    }
}