<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Change Password</title>

<style>

body {

    font-family: Arial, sans-serif;

    background-color: #f2f2f2;

    text-align: center;
}

.container {

    width: 400px;

    margin: 50px auto;

    background: white;

    padding: 20px;

    border-radius: 10px;
}

input {

    width: 90%;

    padding: 10px;

    margin: 10px;
}

button {

    padding: 10px 20px;

    background-color: blue;

    color: white;

    border: none;
}

</style>

</head>

<body>

<div class="container">

    <h2>Change Password</h2>

    <form action="<%= request.getContextPath() %>/change-password"
          method="post">

        <input type="password"
               name="currentPassword"
               placeholder="Current Password"
               required>

        <br>

        <input type="password"
               name="newPassword"
               placeholder="New Password"
               required>

        <br>

        <input type="password"
               name="confirmPassword"
               placeholder="Confirm Password"
               required>

        <br>

        <button type="submit">

            Change Password

        </button>

    </form>

</div>

</body>

</html>