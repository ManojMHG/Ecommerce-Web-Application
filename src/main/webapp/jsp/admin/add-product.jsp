<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.Category" %>

<%
List<Category> categoryList =
        (List<Category>)
        request.getAttribute(
                "categoryList"
        );
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Product</title>

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

    background-color: green;

    color: white;

    border: none;

    cursor: pointer;
}

</style>

</head>

<body>

<div class="container">

    <h2>Add Product</h2>

    <form action="<%= request.getContextPath() %>/add-product"
      method="post"
      enctype="multipart/form-data">

        <select name="categoryId"
        required>

<%
for(Category category : categoryList) {
%>

<option value="<%= category.getCategoryId() %>">

    <%= category.getCategoryName() %>

</option>

<%
}
%>

</select>

        <input type="text"
               name="productName"
               placeholder="Product Name"
               required>

        <textarea name="description"
                  placeholder="Description"
                  required></textarea>

        <input type="number"
               step="0.01"
               name="price"
               placeholder="Price"
               required>

        <input type="number"
               name="stockQuantity"
               placeholder="Stock Quantity"
               required>

        <label>

    Product Image

</label>

<br><br>

<input type="file"
       name="productImage"
       accept="image/*"
       required>

        <input type="text"
               name="brand"
               placeholder="Brand"
               required>

        <button type="submit">

            Add Product

        </button>

    </form>

</div>

</body>

</html>