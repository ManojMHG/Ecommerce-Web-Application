package com.ecommerce.controller.admin;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit-product")
public class EditProductServlet extends HttpServlet {

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

        int productId =
                Integer.parseInt(
                        request.getParameter("productId")
                );

        ProductDAO productDAO =
                new ProductDAO();

        Product product =
                productDAO.getProductById(productId);

        request.setAttribute(
                "product",
                product
        );

        request.getRequestDispatcher(
                "/jsp/admin/edit-product.jsp"
        ).forward(request, response);
    }
}