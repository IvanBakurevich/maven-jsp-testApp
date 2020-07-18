<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>

<form method="post" action="/add">
    Add New User
    <label for="first-name">First Name
        <input type="text" id="first-name" name="first-name">
    </label>
    <label for="last-name">Last Name
        <input type="text" id="last-name" name="last-name">
    </label>
    <input type="submit" value="Add">
</form>
</body>
</html>