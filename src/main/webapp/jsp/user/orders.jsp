<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.OrderItem" %>

<%
    List<OrderItem> orderItemList =
            (List<OrderItem>) request.getAttribute("orderItemList");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Order History</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;
}

.container {

    width: 90%;

    margin: auto;
}

.order-card {

    background-color: white;

    padding: 25px;

    margin: 20px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);

    display: flex;

    align-items: center;

    transition: 0.3s;
}

.order-card:hover {

    transform: translateY(-3px);

    box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
}

.order-card img {

    width: 140px;

    height: 140px;

    object-fit: cover;

    margin-right: 25px;

    border-radius: 10px;
}

.details {

    flex-grow: 1;
}

.invoice-btn {

    background-color: #198754;

    color: white;

    border: none;

    padding: 10px 15px;

    border-radius: 5px;

    cursor: pointer;
}

.invoice-btn:hover {

    background-color: #146c43;
}

</style>

</head>

<body>

<div class="container">

    <h2>My Orders</h2>

    <%
        if(orderItemList != null
                && !orderItemList.isEmpty()) {

            for(OrderItem item : orderItemList) {
    %>

    <div class="order-card">

        <img src="<%= request.getContextPath() %>/<%= item.getImageUrl() %>"
             alt="Product Image"
             onerror="this.src='<%= request.getContextPath() %>/images/no-image.png';">

        <div class="details">

            <h3>

                <%= item.getProductName() %>

            </h3>

            <p>

                Order ID:
                <%= item.getOrderId() %>

            </p>

            <p>

    Status:

    <strong style="
    color:
    <%= "DELIVERED".equals(item.getOrderStatus()) ? "green" :
        "SHIPPED".equals(item.getOrderStatus()) ? "orange" :
        "PLACED".equals(item.getOrderStatus()) ? "#0d6efd" :
        "red" %>;
    ">

        <%= item.getOrderStatus() %>

    </strong>

</p>

            <p>

                Quantity:
                <%= item.getQuantity() %>

            </p>

            <p style="
color:#198754;
font-size:20px;
font-weight:bold;">

    ₹ <%= item.getPrice() %>

</p>

            <br>

            <a href="<%= request.getContextPath() %>/download-invoice?orderId=<%= item.getOrderId() %>">

                <button class="invoice-btn"
        style="
        padding:10px 20px;
        font-size:15px;">

                    Download Invoice

                </button>

            </a>

        </div>

    </div>

    <%
            }
        } else {
    %>

    <h3>

        No Orders Found

    </h3>

    <%
        }
    %>

</div>

</body>

</html>