package com.ecommerce.controller;

import java.io.IOException;

import java.util.List;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch Products

        ProductDAO productDAO = new ProductDAO();

        String keyword =
                request.getParameter("keyword");

        String categoryIdParam =
                request.getParameter("categoryId");

        List<Product> productList;

        if (keyword != null
                && !keyword.trim().isEmpty()) {

            productList =
                    productDAO.searchProducts(keyword);

        } else if (categoryIdParam != null
                && !categoryIdParam.isEmpty()) {

            int categoryId =
                    Integer.parseInt(categoryIdParam);

            productList =
                    productDAO.getProductsByCategory(categoryId);

        } else {

            productList =
                    productDAO.getAllProducts();
        }

        // Store in Request Scope

        request.setAttribute(
                "productList",
                productList
        );

        // Forward to JSP

        request.getRequestDispatcher(
                "/jsp/user/products.jsp"
        ).forward(request, response);
    }
}