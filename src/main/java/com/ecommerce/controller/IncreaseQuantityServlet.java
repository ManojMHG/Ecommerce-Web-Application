package com.ecommerce.controller;

import java.io.IOException;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/increase-quantity")
public class IncreaseQuantityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/jsp/user/login.jsp"
            );

            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        CartDAO cartDAO =
                new CartDAO();

        ProductDAO productDAO =
                new ProductDAO();

        int cartId =
                cartDAO.getCartIdByUserId(
                        user.getUserId()
                );

        Product product =
                productDAO.getProductById(productId);

        int currentQuantity =
                cartDAO.getQuantity(
                        cartId,
                        productId
                );

        if (currentQuantity
                < product.getStockQuantity()) {

            cartDAO.increaseQuantity(
                    cartId,
                    productId
            );

        } else {

            session.setAttribute(
                    "cartMessage",
                    "Stock limit reached"
            );
        }

        response.sendRedirect(
                request.getContextPath()
                + "/cart"
        );
    }
}