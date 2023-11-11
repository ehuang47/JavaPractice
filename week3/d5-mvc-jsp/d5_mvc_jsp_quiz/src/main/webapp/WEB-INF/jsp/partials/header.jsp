<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 11/10/23
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=request.getAttribute("title")%></title>
</head>
<body>
<nav>
    <a href="/register">Register</a>
    <a href="/login">Login</a>
    <a href="/home">Home</a>
    <a href="/quiz">Quiz</a>
    <a href="/contact-us">Contact Us</a>
</nav>
</body>
</html>
