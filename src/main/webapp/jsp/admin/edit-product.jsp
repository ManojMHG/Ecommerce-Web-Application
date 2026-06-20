<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<%@ page import="com.ecommerce.model.Product" %>

<%
    Product product =
            (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Product</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;
}

.container {

    width: 500px;

    margin: 50px auto;

    background-color: white;

    padding: 20px;

    border-radius: 10px;

    box-shadow: 0px 0px 10px gray;
}

input, textarea {

    width: 100%;

    padding: 10px;

    margin-top: 10px;

    margin-bottom: 15px;
}

button {

    width: 100%;

    padding: 10px;

    background-color: orange;

    color: white;

    border: none;

    cursor: pointer;
}

</style>

</head>

<body>

<div class="container">

    <h2>Edit Product</h2>

    <form action="<%= request.getContextPath() %>/update-product"
      method="post"
      enctype="multipart/form-data">

        <input type="hidden"
               name="productId"
               value="<%= product.getProductId() %>">

        <input type="number"
               name="categoryId"
               value="<%= product.getCategoryId() %>"
               required>

        <input type="text"
               name="productName"
               value="<%= product.getProductName() %>"
               required>

        <textarea name="description"
                  required><%= product.getDescription() %></textarea>

        <input type="number"
               step="0.01"
               name="price"
               value="<%= product.getPrice() %>"
               required>

        <input type="number"
               name="stockQuantity"
               value="<%= product.getStockQuantity() %>"
               required>

        <p><b>Current Image:</b></p>

<img src="<%= request.getContextPath() %>/<%= product.getImageUrl() %>"
     width="150"
     height="150">

<br><br>

<input type="hidden"
       name="oldImageUrl"
       value="<%= product.getImageUrl() %>">

<label>Select New Image (Optional)</label>

<input type="file"
       name="imageFile">

        <input type="text"
               name="brand"
               value="<%= product.getBrand() %>"
               required>

        <button type="submit">

            Update Product

        </button>

    </form>

</div>

</body>

</html>