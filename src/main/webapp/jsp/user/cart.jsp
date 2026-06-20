<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="com.ecommerce.model.CartItem" %>

<%
    List<CartItem> cartItemList =
            (List<CartItem>) request.getAttribute("cartItemList");

    double grandTotal = 0;
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Cart</title>

<style>

body {

    font-family: 'Segoe UI', sans-serif;

    background-color: #f2f2f2;
}

.container {

    width: 90%;

    margin: auto;
}

h2 {

    text-align: center;

    margin-top: 25px;

    color: #212529;

    font-size: 32px;
}

.cart-item {

    background-color: white;

    padding: 25px;

    margin: 20px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);

    display: flex;

    align-items: center;

    transition: 0.3s;
}

.cart-item:hover {

    transform: translateY(-3px);

    box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
}
.cart-item img {

    width: 140px;

    height: 140px;

    object-fit: cover;

    margin-right: 25px;

    border-radius: 10px;
}

.details {

    flex-grow: 1;
}

.total {

    text-align: right;

    margin: 30px;

    font-size: 28px;

    font-weight: bold;

    color: green;
}

button {

    background-color: #0d6efd;

    color: white;

    border: none;

    padding: 8px 15px;

    border-radius: 8px;

    cursor: pointer;

    font-weight: bold;
}

button:hover {

    opacity: 0.9;
}

.summary-box {

    width: 350px;

    margin-left: auto;

    background: white;

    padding: 25px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);

    text-align: center;
}
</style>

</head>

<body>

<div class="container">

    <h2>My Cart</h2>

    <%
        if(cartItemList != null && !cartItemList.isEmpty()) {

            for(CartItem item : cartItemList) {

                double total =
                        item.getPrice() * item.getQuantity();

                grandTotal += total;
    %>

    <div class="cart-item">

        <img src="<%= request.getContextPath() %>/<%= item.getImageUrl() %>"
             alt="Product Image">

        <div class="details">

            <h3>

                <%= item.getProductName() %>

            </h3>

           <p style="
color:#198754;
font-size:22px;
font-weight:bold;">

    ₹ <%= item.getPrice() %>

</p>

            <p>

    Quantity:

    <a href="<%= request.getContextPath() %>/decrease-quantity?productId=<%= item.getProductId() %>">

        <button>-</button>

    </a>

    <span style="
    margin:0 15px;
    font-size:20px;
    font-weight:bold;">

        <%= item.getQuantity() %>

    </span>

    <a href="<%= request.getContextPath() %>/increase-quantity?productId=<%= item.getProductId() %>">

        <button>+</button>

    </a>

</p>

            <p style="
font-size:20px;
font-weight:bold;
color:#0d6efd;">

    Total: ₹ <%= total %>

</p>
            
            <a href="<%= request.getContextPath() %>/remove-cart-item?cartItemId=<%= item.getCartItemId() %>">

   <button style="
background-color:#dc3545;">

    Remove

</button>

</a>

        </div>

    </div>

    <%
            }
        } else {
    %>

    <h3 style="
text-align:center;
margin-top:50px;
color:#6c757d;">

    Your Cart Is Empty 🛒

</h3>

    <%
        }
    %>

    <div class="summary-box">

    <div class="total">

        Grand Total

        <br><br>

        ₹ <%= grandTotal %>

    </div>

    <a href="<%= request.getContextPath() %>/checkout">

        <button style="
padding:15px 35px;
font-size:18px;
background-color:#198754;
border-radius:10px;">

            Checkout

        </button>

    </a>

</div>

</div>

</body>

</html>