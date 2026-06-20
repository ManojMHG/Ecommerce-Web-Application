<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.User" %>

<%
List<User> userList =
        (List<User>)
        request.getAttribute(
                "userList"
        );
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Manage Users</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;
}

table {

    width: 90%;

    margin: 30px auto;

    border-collapse: collapse;

    background: white;
}

th,
td {

    border: 1px solid #ddd;

    padding: 12px;

    text-align: center;
}

th {

    background-color: #212529;

    color: white;
}

button {

    background-color: red;

    color: white;

    border: none;

    padding: 8px 12px;

    cursor: pointer;
}

</style>

</head>

<body>

<h2 style="text-align:center;">

    Manage Users

</h2>

<table>

<tr>

    <th>ID</th>

    <th>Name</th>

    <th>Email</th>

    <th>Phone</th>

    <th>Role</th>

    <th>Action</th>

</tr>

<%
if(userList != null) {

    for(User user : userList) {
%>

<tr>

    <td>
        <%= user.getUserId() %>
    </td>

    <td>
        <%= user.getName() %>
    </td>

    <td>
        <%= user.getEmail() %>
    </td>

    <td>
        <%= user.getPhone() %>
    </td>

    <td>
        <%= user.getRole() %>
    </td>

    <td>

        <a href="<%= request.getContextPath() %>/admin/delete-user?userId=<%= user.getUserId() %>"
   onclick="return confirm('Are you sure you want to delete this user?');">

            <button>

                Delete

            </button>

        </a>

    </td>

</tr>

<%
    }
}
%>

</table>

</body>

</html>