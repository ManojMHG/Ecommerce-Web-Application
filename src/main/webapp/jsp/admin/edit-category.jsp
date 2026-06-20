<%@ page import="com.ecommerce.model.Category" %>

<%
Category category =
        (Category)
        request.getAttribute(
                "category"
        );
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Category</title>
</head>

<body>

<h2>Edit Category</h2>

<form action="<%= request.getContextPath() %>/update-category"
      method="post">

    <input type="hidden"
           name="categoryId"
           value="<%= category.getCategoryId() %>">

    <input type="text"
           name="categoryName"
           value="<%= category.getCategoryName() %>"
           required>

    <br><br>

    <textarea name="description"><%= category.getDescription() %></textarea>

    <br><br>

    <button type="submit">

        Update Category

    </button>

</form>

</body>

</html>