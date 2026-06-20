package com.ecommerce.controller.admin;

import java.io.IOException;

import com.ecommerce.dao.AdminDashboardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import com.ecommerce.model.SalesReport;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        AdminDashboardDAO dao =
                new AdminDashboardDAO();

        request.setAttribute(
                "totalProducts",
                dao.getTotalProducts()
        );

        request.setAttribute(
                "totalUsers",
                dao.getTotalUsers()
        );

        request.setAttribute(
                "totalOrders",
                dao.getTotalOrders()
        );

        request.setAttribute(
                "totalRevenue",
                dao.getTotalRevenue()
        );
        
        request.setAttribute(
                "averageOrderValue",
                dao.getAverageOrderValue()
        );
        
        request.setAttribute(
                "deliveredOrders",
                dao.getDeliveredOrders()
        );
        
        List<SalesReport> reportList =
                dao.getTopSellingProducts();

        request.setAttribute(
                "reportList",
                reportList
        );

        request.getRequestDispatcher(
                "/jsp/admin/dashboard.jsp"
        ).forward(request, response);
    }
}