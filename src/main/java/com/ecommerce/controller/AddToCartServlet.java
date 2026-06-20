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

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

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

        int userId = user.getUserId();

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        ProductDAO productDAO =
                new ProductDAO();

        Product product =
                productDAO.getProductById(productId);

        CartDAO cartDAO = new CartDAO();

        int cartId =
                cartDAO.getCartIdByUserId(userId);

        if (cartId == 0) {

            cartId =
                    cartDAO.createCart(userId);
        }

        boolean status = false;

        if (cartDAO.productExistsInCart(cartId, productId)) {

            int currentQuantity =
                    cartDAO.getQuantity(
                            cartId,
                            productId
                    );

            if (currentQuantity
                    < product.getStockQuantity()) {

                status =
                        cartDAO.increaseQuantity(
                                cartId,
                                productId
                        );

            } else {

                response.getWriter().println(
                        "<h2>Stock Limit Reached</h2>"
                );

                return;
            }

        } else {

            if (product.getStockQuantity() > 0) {

                status =
                        cartDAO.addToCart(
                                cartId,
                                productId,
                                1
                        );

            } else {

                response.getWriter().println(
                        "<h2>Product Out Of Stock</h2>"
                );

                return;
            }
        }

        if (status) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/products"
            );

        } else {

            response.getWriter().println(
                    "<h2>Failed To Add Product To Cart</h2>"
            );
        }
    }
}