package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download-invoice")
public class DownloadInvoiceServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"
                        )
                );

        OrderDAO orderDAO =
                new OrderDAO();

        Order order =
                orderDAO.getOrderById(
                        orderId
                );

        List<OrderItem> orderItemList =
                orderDAO.getOrderItemsByOrderId(
                        orderId
                );

        response.setContentType(
                "application/pdf"
        );

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=invoice_"
                + orderId
                + ".pdf"
        );

        try {

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );

            document.open();

            document.add(
                    new Paragraph(
                            "====================================="
                    )
            );

            document.add(
                    new Paragraph(
                            "          E-COMMERCE STORE"
                    )
            );

            document.add(
                    new Paragraph(
                            "====================================="
                    )
            );

            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                    		"Invoice No : INV-"
                    				+ orderId
                    )
            );

            document.add(
                    new Paragraph(
                            "Customer Name : "
                            + order.getUserName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Email: "
                            + order.getEmail()
                    )
            );

            document.add(
                    new Paragraph(
                            "Order Date: "
                            + order.getOrderDate()
                    )
            );

            document.add(
                    new Paragraph(
                            "Status: "
                            + order.getOrderStatus()
                    )
            );

            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                            "Products"
                    )
            );
            
            document.add(
                    new Paragraph(
                            "-------------------------------------"
                    )
            );

            document.add(
                    new Paragraph(
                            "Product      Qty      Price"
                    )
            );

            document.add(
                    new Paragraph(
                            "-------------------------------------"
                    )
            );

            for(OrderItem item
                    : orderItemList) {

                document.add(
                        new Paragraph(
                        		item.getProductName()
                        		+ "    Qty:"
                        		+ item.getQuantity()
                        		+ "    ₹"
                        		+ item.getPrice()
                        )
                );
            }

            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                    		"-------------------------------------"
                    )
            );
            
            document.add(
                    new Paragraph(
                            "Total Amount : ₹"
                            + order.getTotalAmount()
                    )
            );
            
            document.add(
                    new Paragraph(
                            " "
                    )
            );

            document.add(
                    new Paragraph(
                            "Thank You For Shopping With Us"
                    )
            );

            document.add(
                    new Paragraph(
                            "====================================="
                    )
            );

            document.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}