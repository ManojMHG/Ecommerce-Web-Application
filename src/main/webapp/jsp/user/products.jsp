<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="com.ecommerce.model.Product" %>
<%@ page import="com.ecommerce.dao.ReviewDAO" %>
<%@ page import="com.ecommerce.model.Review" %>

<%
    List<Product> productList =
            (List<Product>) request.getAttribute("productList");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Products</title>

<style>

body {

    font-family: 'Segoe UI', sans-serif;

    background-color: #f4f6f9;

    margin: 0;

    padding: 0;
}

.container {

    width: 95%;

    margin: auto;
}

.products-grid {

    display: flex;

    flex-wrap: wrap;

    justify-content: center;

    gap: 15px;
}

.top-bar {

    background-color: #212529;

    padding: 15px;

    text-align: right;
}

.top-bar a {

    color: white;

    text-decoration: none;

    margin-left: 20px;

    font-weight: bold;
}

.top-bar a:hover {

    color: #ffc107;
}

h2 {

    text-align: center;

    color: #212529;

    margin-top: 25px;
}

.product-card {

    background-color: white;

    padding: 20px;

    margin: 15px;

    border-radius: 15px;

    box-shadow: 0 4px 12px rgba(0,0,0,0.1);

    display: inline-block;

    width: 280px;

    vertical-align: top;

    transition: transform 0.3s ease;
}

.product-card:hover {

    transform: translateY(-5px);
}

.product-card img {

    width: 100%;

    height: 220px;

    object-fit: cover;

    border-radius: 10px;
}

.product-card h3 {

    color: #212529;
}

button {

    padding: 10px 15px;

    border: none;

    border-radius: 8px;

    cursor: pointer;

    font-weight: bold;
}

button:hover {

    opacity: 0.9;
}

.search-box {

    text-align: center;

    margin: 20px;
}

.search-box input {

    width: 350px;

    padding: 12px;

    border-radius: 8px;

    border: 1px solid #ccc;
}

.search-box button {

    background-color: #0d6efd;

    color: white;
}

.category-buttons {

    text-align: center;

    margin-bottom: 25px;
}

.category-buttons button {

    background-color: #6c757d;

    color: white;

    margin: 5px;
}

.invoice-btn,
.cart-btn {

    background-color: #198754;

    color: white;
}

.delete-btn {

    background-color: #dc3545;

    color: white;
}

.edit-btn {

    background-color: #fd7e14;

    color: white;
}

</style>

</head>

<body>

<div class="container">

<nav style="
background:#212529;
padding:15px;
margin-bottom:20px;
">

<a href="<%= request.getContextPath() %>/products"
   style="
   color:white;
   text-decoration:none;
   font-size:22px;
   font-weight:bold;
   margin-right:40px;
   ">

   E-Commerce Store

</a>

<a href="<%= request.getContextPath() %>/products"
   style="color:white; margin-right:20px;">

   Home

</a>

<a href="<%= request.getContextPath() %>/cart"
   style="color:white; margin-right:20px;">

   Cart

</a>

<a href="<%= request.getContextPath() %>/order-history"
   style="color:white; margin-right:20px;">

   Orders

</a>

<a href="<%= request.getContextPath() %>/profile"
   style="color:white; margin-right:20px;">

   Profile

</a>

<a href="<%= request.getContextPath() %>/wishlist"
   style="color:white; margin-right:20px;">

   Wishlist

</a>

<a href="<%= request.getContextPath() %>/logout"
   style="color:red;">

   Logout

</a>

</nav>

<h2>All Products</h2>

<div class="search-box">

    <form action="<%= request.getContextPath() %>/products"
          method="get">

        <input type="text"
               name="keyword"
               placeholder="Search products..."
               style="padding:10px; width:350px; border-radius:8px; border:1px solid #ccc;">

        <button type="submit">

            Search

        </button>

    </form>

</div>

<div class="category-buttons">

    <a href="<%= request.getContextPath() %>/products">

        <button>All</button>

    </a>

    <a href="<%= request.getContextPath() %>/products?categoryId=1">

        <button>Electronics</button>

    </a>

    <a href="<%= request.getContextPath() %>/products?categoryId=2">

        <button>Clothing</button>

    </a>

    <a href="<%= request.getContextPath() %>/products?categoryId=3">

        <button>Shoes</button>

    </a>

    <a href="<%= request.getContextPath() %>/products?categoryId=4">

        <button>Books</button>

    </a>

    <a href="<%= request.getContextPath() %>/products?categoryId=5">

        <button>Home Appliances</button>

    </a>

</div>

<div class="products-grid">

    <%
        if(productList != null) {

            for(Product product : productList) {
    %>

    <div class="product-card">

        <img src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
     alt="Product Image"
     onerror="this.src='<%= request.getContextPath() %>/images/no-image.png';">

        <h3>

            <%= product.getProductName() %>

        </h3>

        <p>

            Brand:
            <%= product.getBrand() %>

        </p>
        
        <%
ReviewDAO ratingDAO =
        new ReviewDAO();

double averageRating =
        ratingDAO.getAverageRating(
                product.getProductId()
        );
%>

<p>

    Average Rating:

<%
int roundedRating =
        (int) Math.round(
                averageRating
        );

for(int i = 1;
        i <= roundedRating;
        i++) {
%>

⭐

<%
}
%>

(
<%= String.format("%.1f",
        averageRating) %>
)

</p>

        <p>

            Price:
            ₹ <%= product.getPrice() %>

        </p>

        <p>

            Stock:
            <%= product.getStockQuantity() %>

        </p>
       <%
if(product.getStockQuantity() > 0) {
%>

<a href="<%= request.getContextPath() %>/add-to-cart?productId=<%= product.getProductId() %>">

    <button class="cart-btn">

    Add To Cart

</button>

</a>

<%
} else {
%>

<button disabled>

    Out Of Stock

</button>

<%
}
%>


<br><br>

<a href="<%= request.getContextPath() %>/add-to-wishlist?productId=<%= product.getProductId() %>">

    <button style="
    background-color:#dc3545;
    color:white;">

        ❤️ Wishlist

    </button>

</a>

<br><br>

<%
if(session.getAttribute("loggedInAdmin") != null) {
%>

<a href="<%= request.getContextPath() %>/delete-product?productId=<%= product.getProductId() %>">

    <button class="delete-btn">

        Delete Product

    </button>

</a>

<br><br>

<a href="<%= request.getContextPath() %>/edit-product?productId=<%= product.getProductId() %>">

    <button class="edit-btn">

    Edit Product

</button>

</a>

<%
}
%>

<hr>

<h4>Reviews</h4>

<button
onclick="document.getElementById('reviews<%= product.getProductId() %>').style.display='block';">

    Show Reviews

</button>

<br><br>

<div id="reviews<%= product.getProductId() %>"
     style="display:none;">

<%
ReviewDAO reviewDAO =
        new ReviewDAO();

List<Review> reviewList =
        reviewDAO.getReviewsByProductId(
                product.getProductId()
        );

for(Review review : reviewList) {
%>

<p>

    <b><%= review.getUserName() %></b>

<%
for(int i = 1; i <= review.getRating(); i++) {
%>

⭐

<%
}
%>

</p>

<p>

    <%= review.getReviewText() %>

</p>

<hr>

<%
}
%>

</div>

<%
if(session.getAttribute("loggedInUser") != null) {
%>

<button
onclick="document.getElementById('reviewForm<%= product.getProductId() %>').style.display='block';">

    Write Review

</button>

<br><br>

<div id="reviewForm<%= product.getProductId() %>"
     style="display:none;">

<form action="<%= request.getContextPath() %>/add-review"
      method="post">

    <input type="hidden"
           name="productId"
           value="<%= product.getProductId() %>">

    Rating:

    <input type="number"
           name="rating"
           style="width:60px; padding:5px;"
           min="1"
           max="5"
           required>

    <br><br>

    <textarea name="reviewText"
          style="width:100%; padding:8px;"
              placeholder="Write your review"
              required></textarea>

    <br><br>

    <button type="submit">

        Submit Review

    </button>

</form>

</div>

<%
}
%>
    </div>
   

<%
            }
        }
%>

</div>

</body>

</html>