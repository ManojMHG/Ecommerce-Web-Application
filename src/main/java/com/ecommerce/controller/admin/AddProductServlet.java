package com.ecommerce.controller.admin;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import java.io.File;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-product")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get Form Data
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
                        request.getParameter("categoryId")
                );

        String productName =
                request.getParameter("productName");

        String description =
                request.getParameter("description");

        double price =
                Double.parseDouble(
                        request.getParameter("price")
                );

        int stockQuantity =
                Integer.parseInt(
                        request.getParameter("stockQuantity")
                );
        
        

        Part imagePart =
                request.getPart(
                        "productImage"
                );

        String fileName =
                imagePart.getSubmittedFileName();
        
        String uploadPath =
                getServletContext()
                .getRealPath("")
                + File.separator
                + "uploads";
        
        System.out.println(
                "Upload Path: "
                + uploadPath
        );

        File uploadDir =
                new File(uploadPath);

        if(!uploadDir.exists()) {

            uploadDir.mkdir();
        }
        
        
        String imagePath =
                uploadPath
                + File.separator
                + fileName;

        imagePart.write(
                imagePath
        );

        String brand =
                request.getParameter("brand");

        // Create Product Object

        Product product =
                new Product();

        product.setCategoryId(categoryId);

        product.setProductName(productName);

        product.setDescription(description);

        product.setPrice(price);

        product.setStockQuantity(stockQuantity);

        product.setImageUrl(
                "uploads/"
                + fileName
        );

        product.setBrand(brand);

        // DAO Operation

        ProductDAO productDAO =
                new ProductDAO();

        boolean status =
                productDAO.addProduct(product);

        // Response

        if (status) {

            response.getWriter().println(
                    "<h2>Product Added Successfully</h2>"
            );

        } else {

            response.getWriter().println(
                    "<h2>Failed To Add Product</h2>"
            );
        }
    }
}