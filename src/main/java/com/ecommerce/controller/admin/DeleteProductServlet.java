package com.ecommerce.controller.admin;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {

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

        boolean status =
                productDAO.deleteProduct(productId);

        if (status) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/products"
            );

        } else {

            response.getWriter().println(
                    "<h2>Failed To Delete Product</h2>"
            );
        }
    }
}