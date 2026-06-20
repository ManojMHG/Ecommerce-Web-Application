<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Order" %>

<%
List<Order> orderList =
        (List<Order>) request.getAttribute("orderList");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>All Orders</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;
}

table {

    width: 95%;

    margin: 30px auto;

    border-collapse: collapse;

    background-color: white;
}

th, td {

    border: 1px solid #ddd;

    padding: 12px;

    text-align: center;
}

th {

    background-color: #333;

    color: white;
}

h2 {

    text-align: center;
}

</style>

</head>

<body>

<h2>All Customer Orders</h2>

<table>

<tr>

    <th>Order ID</th>

    <th>Customer</th>

    <th>Email</th>

    <th>Total Amount</th>

    <th>Status</th>
    <th>Update Status</th>

    <th>Order Date</th>

</tr>

<%
if(orderList != null) {

    for(Order order : orderList) {
%>

<tr>

    <td><%= order.getOrderId() %></td>

    <td><%= order.getUserName() %></td>

    <td><%= order.getEmail() %></td>

    <td>₹ <%= order.getTotalAmount() %></td>

    <td>

    <%= order.getOrderStatus() %>

</td>

<td>

    <form action="<%= request.getContextPath() %>/admin/update-order-status"
          method="post">

        <input type="hidden"
               name="orderId"
               value="<%= order.getOrderId() %>">

        <select name="orderStatus">

            <option value="PLACED">
                PLACED
            </option>

            <option value="SHIPPED">
                SHIPPED
            </option>

            <option value="DELIVERED">
                DELIVERED
            </option>

        </select>

        <button type="submit">

            Update

        </button>

    </form>

</td>

    <td><%= order.getOrderDate() %></td>

</tr>

<%
    }
}
%>

</table>

</body>

</html>