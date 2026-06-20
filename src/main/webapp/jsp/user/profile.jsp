<%@ page import="com.ecommerce.model.User" %>

<%
User user =
        (User) session.getAttribute(
                "loggedInUser"
        );
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Profile</title>

<style>

body {

    font-family: 'Segoe UI', sans-serif;

    background-color: #f4f6f9;

    text-align: center;

    margin: 0;

    padding: 0;
}
.profile {

    background: white;

    width: 550px;

    margin: 40px auto;

    padding: 30px;

    border-radius: 15px;

    box-shadow: 0px 4px 15px rgba(0,0,0,0.15);
}

input,
textarea {

    width: 95%;

    padding: 12px;

    margin-top: 5px;

    margin-bottom: 15px;

    border: 1px solid #ccc;

    border-radius: 8px;
}

button {

    padding: 12px 25px;

    background-color: #198754;

    color: white;

    border: none;

    border-radius: 8px;

    cursor: pointer;

    font-weight: bold;
}

button:hover {

    background-color: #157347;
}


h2 {

    color: #212529;

    margin-bottom: 25px;
}
button:hover {

    background-color: darkgreen;
}

.readonly-field {

    font-size: 18px;

    margin: 15px;

    padding: 12px;

    background-color: #f8f9fa;

    border-radius: 8px;

    text-align: left;
}

#editSection {

    display: none;
}

.link-button {

    display: inline-block;

    margin-right: 15px;

    text-decoration: none;

    color: blue;

    font-size: 18px;
}

</style>

<script>

function showEditForm() {

    document.getElementById(
            "viewSection"
    ).style.display = "none";

    document.getElementById(
            "editSection"
    ).style.display = "block";
}

function cancelEdit() {

    document.getElementById(
            "editSection"
    ).style.display = "none";

    document.getElementById(
            "viewSection"
    ).style.display = "block";
}

</script>

</head>

<body>

<div class="profile">

    <h2>My Profile</h2>

    <!-- VIEW MODE -->

    <div id="viewSection">

        <p class="readonly-field">

            <b>Name:</b>
            <%= user.getName() %>

        </p>

        <p class="readonly-field">

            <b>Email:</b>
            <%= user.getEmail() %>

        </p>

        <p class="readonly-field">

            <b>Phone:</b>
            <%= user.getPhone() %>

        </p>

        <p class="readonly-field">

            <b>Address:</b>
            <%= user.getAddress() %>

        </p>

        <br>

        <button onclick="showEditForm()">

            Edit Profile

        </button>

        &nbsp;&nbsp;

        <a class="link-button"
           href="<%= request.getContextPath() %>/jsp/user/change-password.jsp">

            Change Password

        </a>

    </div>

    <!-- EDIT MODE -->

    <div id="editSection">

        <form action="<%= request.getContextPath() %>/update-profile"
              method="post">

            Name:

            <br>

            <input type="text"
                   name="name"
                   value="<%= user.getName() %>"
                   required>

            <br>

            Email:

            <br>

            <input type="email"
                   name="email"
                   value="<%= user.getEmail() %>"
                   required>

            <br>

            Phone:

            <br>

            <input type="text"
                   name="phone"
                   value="<%= user.getPhone() %>"
                   required>
                   
                   
             <br>

<label>

    <input type="checkbox"
           name="otpEnabled"
           <%= user.isOtpEnabled()
               ? "checked"
               : "" %>>

    Enable OTP Login

</label>

<br><br>

            <br>

            Address:

            <br>

            <textarea name="address"
                      rows="4"
                      required><%= user.getAddress() %></textarea>

            <br><br>

            <button type="submit">

                Update Profile

            </button>

            &nbsp;&nbsp;

            <button type="button"
                    onclick="cancelEdit()">

                Cancel

            </button>

        </form>

    </div>

</div>

</body>

</html>