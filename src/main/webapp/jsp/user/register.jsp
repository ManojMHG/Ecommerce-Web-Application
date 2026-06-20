<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>User Registration</title>

<style>

body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
}

.container {

    width: 400px;

    margin: 50px auto;

    background-color: white;

    padding: 20px;

    border-radius: 10px;

    box-shadow: 0px 0px 10px gray;
}

h2 {
    text-align: center;
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

    background-color: blue;

    color: white;

    border: none;

    cursor: pointer;
}

button:hover {
    background-color: darkblue;
}

</style>

</head>

<body>

<div class="container">

    <h2>User Registration</h2>

    <form action="<%= request.getContextPath() %>/register"
          method="post">

        <input type="text"
               name="name"
               placeholder="Enter Name"
               required>

        <input type="email"
               name="email"
               placeholder="Enter Email"
               required>

        <input type="password"
               name="password"
               placeholder="Enter Password"
               required>

        <input type="text"
               name="phone"
               placeholder="Enter Phone Number"
               required>

        <textarea name="address"
                  placeholder="Enter Address"
                  rows="4"
                  required></textarea>

        <button type="submit">

            Register

        </button>

    </form>

</div>

</body>

</html>