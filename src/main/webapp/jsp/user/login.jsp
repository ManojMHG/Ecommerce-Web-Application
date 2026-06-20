<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Login</title>

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

h2 {

    text-align: center;
}

input[type="email"],
input[type="password"] {

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

button:hover {

    background-color: darkgreen;
}

.login-type {

    text-align: center;

    margin-bottom: 20px;
}

</style>

</head>

<body>

<div class="container">

    <h2>Login</h2>

    <form action="<%= request.getContextPath() %>/login-portal"
          method="post">

        <div class="login-type">

            <strong>Login As</strong>

            <br><br>

            <input type="radio"
                   name="loginType"
                   value="USER"
                   checked>

            User

            &nbsp;&nbsp;&nbsp;&nbsp;

            <input type="radio"
                   name="loginType"
                   value="ADMIN">

            Admin

        </div>

        <input type="email"
               name="email"
               placeholder="Enter Email"
               required>

        <input type="password"
               name="password"
               placeholder="Enter Password"
               required>

        <button type="submit">

            Login

        </button>
        
        <br><br>

New User?

<a href="<%= request.getContextPath() %>/jsp/user/register.jsp">

    Register Here

</a>

        <br><br>

        <a href="<%= request.getContextPath() %>/jsp/user/forgot-password.jsp">

            Forgot Password?

        </a>
        
 

    </form>

</div>

</body>

</html>