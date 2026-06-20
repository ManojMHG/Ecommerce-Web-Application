<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Admin Login</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;
}

.container {

    width: 400px;

    margin: 100px auto;

    background-color: white;

    padding: 20px;

    border-radius: 10px;

    box-shadow: 0px 0px 10px gray;
}

input {

    width: 100%;

    padding: 10px;

    margin-top: 10px;

    margin-bottom: 15px;
}

button {

    width: 100%;

    padding: 10px;

    background-color: black;

    color: white;

    border: none;

    cursor: pointer;
}

</style>

</head>

<body>

<div class="container">

    <h2>Admin Login</h2>

    <form action="<%= request.getContextPath() %>/admin-login"
          method="post">

        <input type="email"
               name="email"
               placeholder="Admin Email"
               required>

        <input type="password"
               name="password"
               placeholder="Password"
               required>

        <button type="submit">

            Login

        </button>

    </form>

</div>

</body>

</html>