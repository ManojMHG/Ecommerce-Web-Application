<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Reset Password</title>

</head>

<body style="text-align:center; margin-top:100px;">

<h2>Reset Password</h2>

<form action="<%= request.getContextPath() %>/reset-password"
      method="post">

    <input type="password"
           name="password"
           placeholder="New Password"
           required>

    <br><br>

    <button type="submit">

        Update Password

    </button>

</form>

</body>

</html>