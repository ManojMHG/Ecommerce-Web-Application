<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Forgot Password</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;

    text-align: center;
}

.container {

    width: 400px;

    margin: 100px auto;

    background: white;

    padding: 30px;

    border-radius: 10px;

    box-shadow: 0px 0px 10px gray;
}

input {

    width: 80%;

    padding: 10px;

    margin: 15px;
}

button {

    padding: 10px 20px;

    background-color: green;

    color: white;

    border: none;

    border-radius: 5px;

    cursor: pointer;
}

</style>

</head>

<body>

<div class="container">

    <h2>Forgot Password</h2>

    <form action="<%= request.getContextPath() %>/forgot-password"
          method="post">

        <input type="email"
               name="email"
               placeholder="Enter Email"
               required>

        <br>

        <button type="submit">

            Send OTP

        </button>

    </form>

</div>

</body>

</html>