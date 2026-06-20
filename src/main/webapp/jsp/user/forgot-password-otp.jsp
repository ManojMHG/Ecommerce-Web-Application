<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Verify OTP</title>

</head>

<body style="text-align:center; margin-top:100px;">

<h2>Verify OTP</h2>

<form action="<%= request.getContextPath() %>/verify-forgot-otp"
      method="post">

    <input type="text"
           name="otp"
           placeholder="Enter OTP"
           required>

    <br><br>

    <button type="submit">

        Verify OTP

    </button>

</form>

</body>

</html>