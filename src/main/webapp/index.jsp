<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>E-Commerce Store</title>

<style>

body {

    font-family: 'Segoe UI', sans-serif;

    margin: 0;

    background-color: #f4f6f9;
}

.header {

    background-color: #212529;

    color: white;

    padding: 20px;

    text-align: center;
}

.hero {

    text-align: center;

    padding: 80px 20px;
}

.hero h1 {

    font-size: 48px;

    color: #212529;
}

.hero p {

    font-size: 20px;

    color: #6c757d;
}

.btn {

    display: inline-block;

    padding: 15px 30px;

    margin: 15px;

    text-decoration: none;

    color: white;

    border-radius: 10px;

    font-size: 18px;

    font-weight: bold;
}

.login-btn {

    background-color: #0d6efd;
}

.register-btn {

    background-color: #198754;
}

.features {

    width: 80%;

    margin: auto;

    margin-top: 40px;

    display: flex;

    justify-content: space-around;

    flex-wrap: wrap;
}

.feature {

    background-color: white;

    width: 250px;

    padding: 20px;

    margin: 15px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.1);

    text-align: center;
}

.footer {

    margin-top: 60px;

    background-color: #212529;

    color: white;

    text-align: center;

    padding: 20px;
}

</style>

</head>

<body>

<div class="header">

```
<h2>E-Commerce Store</h2>
```

</div>

<div class="hero">

```
<h1>Welcome To E-Commerce Store</h1>

<p>

    Buy Electronics, Clothing, Shoes, Books and More

</p>

<a href="<%= request.getContextPath() %>/jsp/user/login.jsp"
   class="btn login-btn">

    Login

</a>

<a href="<%= request.getContextPath() %>/jsp/user/register.jsp"
   class="btn register-btn">

    Register

</a>
```

</div>

<div class="features">

```
<div class="feature">

    <h3>Secure Login</h3>

    <p>

        OTP Authentication and Password Security

    </p>

</div>

<div class="feature">

    <h3>Wishlist</h3>

    <p>

        Save Products For Later Purchase

    </p>

</div>

<div class="feature">

    <h3>Fast Checkout</h3>

    <p>

        Easy Cart and Order Placement

    </p>

</div>

<div class="feature">

    <h3>Email Notifications</h3>

    <p>

        Order and Delivery Updates By Email

    </p>

</div>
```

</div>

<div class="footer">

```
© 2025 E-Commerce Store
```

</div>

</body>

</html>
