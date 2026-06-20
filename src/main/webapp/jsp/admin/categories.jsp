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

<title>Categories</title>

</head>

<body>

<h2>All Categories</h2>

<table border="1">

<tr>

    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
<th>Actions</th>

</tr>

<%
for(Category category : categoryList) {
%>

<tr>

    <td>
        <%= category.getCategoryId() %>
    </td>

    <td>
        <%= category.getCategoryName() %>
    </td>

    <td>
        <%= category.getDescription() %>
    </td>
    
    <td>

<a href="<%= request.getContextPath() %>/edit-category?categoryId=<%= category.getCategoryId() %>">

    Edit

</a>

&nbsp;&nbsp;

<a href="<%= request.getContextPath() %>/delete-category?categoryId=<%= category.getCategoryId() %>">

    Delete

</a>

</td>

</tr>

<%
}
%>

</table>

</body>

</html>