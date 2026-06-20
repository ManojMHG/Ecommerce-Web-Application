package com.ecommerce.controller.admin;

import java.io.File;
import java.io.IOException;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/update-product")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

@Override
protected void doPost(
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

    String brand =
            request.getParameter("brand");

    String imageUrl =
            request.getParameter(
                    "oldImageUrl"
            );

    Part imagePart =
            request.getPart(
                    "imageFile"
            );

    if(imagePart != null
            && imagePart.getSize() > 0) {

        String fileName =
                imagePart.getSubmittedFileName();

        String uploadPath =
                getServletContext()
                .getRealPath("")
                + File.separator
                + "uploads";

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

        imageUrl =
                "uploads/"
                + fileName;
    }

    Product product =
            new Product();

    product.setProductId(productId);

    product.setCategoryId(categoryId);

    product.setProductName(productName);

    product.setDescription(description);

    product.setPrice(price);

    product.setStockQuantity(stockQuantity);

    product.setImageUrl(imageUrl);

    product.setBrand(brand);

    ProductDAO productDAO =
            new ProductDAO();

    boolean status =
            productDAO.updateProduct(product);

    if(status) {

        response.sendRedirect(
                request.getContextPath()
                + "/products"
        );

    } else {

        response.getWriter().println(
                "<h2>Failed To Update Product</h2>"
        );
    }
}


}
