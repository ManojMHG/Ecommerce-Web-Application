<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.SalesReport" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Admin Dashboard</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;

    text-align: center;
}

.container {

    margin-top: 40px;
}

.stats {

    display: flex;

    flex-wrap: wrap;

    justify-content: center;

    gap: 20px;

    margin-bottom: 40px;
}

.stat {

    background: white;

    width: 220px;

    padding: 25px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);

    font-size: 18px;

    font-weight: bold;

    transition: 0.3s;
}

.stat:hover {

    transform: translateY(-5px);

    box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
}

a {

    display: inline-block;

    margin: 10px;

    padding: 12px 25px;

    background-color: #0d6efd;

    color: white;

    text-decoration: none;

    border-radius: 8px;

    font-weight: bold;

    transition: 0.3s;
}

a:hover {

    background-color: #0b5ed7;

    transform: scale(1.03);
}

table {

    width: 70%;

    margin: auto;

    border-collapse: collapse;

    background: white;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);
}

th {

    background-color: #212529;

    color: white;

    padding: 12px;
}

td {

    padding: 12px;

    border: 1px solid #ddd;
}

</style>

</head>

<body>

<div class="container">

<nav style="
background:#212529;
padding:15px;
margin-bottom:30px;
display:flex;
justify-content:space-between;
align-items:center;
">

<span style="
color:white;
font-size:24px;
font-weight:bold;
">

Admin Panel

</span>

<a href="<%= request.getContextPath() %>/admin-logout"
   style="
   background-color:red;
   color:white;
   text-decoration:none;
   padding:10px 20px;
   border-radius:8px;
   font-size:16px;
   ">

   Logout

</a>

</nav>

<h1>Admin Dashboard</h1>

<div class="stats">

    <div class="stat">
        Total Products :
        <%= request.getAttribute("totalProducts") %>
    </div>

    <div class="stat">
        Total Users :
        <%= request.getAttribute("totalUsers") %>
    </div>

    <div class="stat">
        Total Orders :
        <%= request.getAttribute("totalOrders") %>
    </div>

    <div class="stat">
        Total Revenue :
        ₹ <%= String.format("%,.2f",
        request.getAttribute("totalRevenue")) %>
    </div>
    
    <div class="stat">

    Average Order Value :

    ₹ <%= String.format("%,.2f",
        (Double)request.getAttribute("averageOrderValue")) %>

</div>

<div class="stat">

    Delivered Orders :

    <%= request.getAttribute(
            "deliveredOrders"
    ) %>

</div>

</div>

<a href="<%= request.getContextPath() %>/add-product-form">
    Add Product
</a>

<a href="<%= request.getContextPath() %>/products">
    View Products
</a>

<a href="<%= request.getContextPath() %>/admin/orders">
    Manage Orders
</a>

<a href="<%= request.getContextPath() %>/jsp/admin/add-category.jsp">

    Add Category

</a>
<a href="<%= request.getContextPath() %>/admin/categories">

    View Categories

</a>

<a href="<%= request.getContextPath() %>/admin/users">

    Manage Users

</a>

<h2>Top Selling Products</h2>

<table border="1"
       style="margin:auto;">

<tr>

    <th>Product</th>

    <th>Total Sold</th>

</tr>

<%
List<SalesReport> reportList =
        (List<SalesReport>)
        request.getAttribute(
                "reportList"
        );

if(reportList != null) {

    for(SalesReport report
            : reportList) {
%>

<tr>

    <td>
        <%= report.getProductName() %>
    </td>

    <td>
        <%= report.getTotalSold() %>
    </td>

</tr>

<%
    }
}
%>

</table>

<br><br>

</div>

</body>

</html>