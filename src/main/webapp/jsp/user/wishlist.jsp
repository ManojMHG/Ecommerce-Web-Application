<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Wishlist" %>

<%
List<Wishlist> wishlist =
        (List<Wishlist>)
        request.getAttribute(
                "wishlist"
        );
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Wishlist</title>

<style>

body {

    font-family: 'Segoe UI', sans-serif;

    background-color: #f4f6f9;
}

.container {

    width: 90%;

    margin: auto;
}

.card {

    background: white;

    padding: 20px;

    margin: 20px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);

    display: flex;

    align-items: center;
}

.card img {

    width: 120px;

    height: 120px;

    object-fit: cover;

    margin-right: 20px;

    border-radius: 10px;
}

button {

    padding: 10px 15px;

    border: none;

    border-radius: 8px;

    color: white;

    cursor: pointer;
}
</style>

</head>

<body>

<div class="container">

<h2>My Wishlist </h2>

<%
if(wishlist != null) {

    for(Wishlist item : wishlist) {
%>

<div class="card">

<img src="<%= request.getContextPath() %>/<%= item.getImageUrl() %>">

<div>

<h3>

<%= item.getProductName() %>

</h3>

<p>

₹ <%= item.getPrice() %>

</p>

<a href="<%= request.getContextPath() %>/remove-from-wishlist?wishlistId=<%= item.getWishlistId() %>">

    <button style="background-color:#dc3545;">

        Remove

    </button>

</a>

<br><br>

<a href="<%= request.getContextPath() %>/wishlist-to-cart?productId=<%= item.getProductId() %>&wishlistId=<%= item.getWishlistId() %>">

    <button style="background-color:#198754;">

        Move To Cart

    </button>

</a>

</div>

</div>

<%
    }
}
%>

</div>

</body>

</html>