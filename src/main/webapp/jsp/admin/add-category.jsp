<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Add Category</title>
</head>

<body>

<h2>Add Category</h2>

<form action="<%= request.getContextPath() %>/add-category"
      method="post">

    <input type="text"
           name="categoryName"
           placeholder="Category Name"
           required>

    <br><br>

    <textarea name="description"
              placeholder="Description"></textarea>

    <br><br>

    <button type="submit">

        Add Category

    </button>

</form>

</body>

</html>