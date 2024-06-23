<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 11/12/23
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <div>
            <label for="email">Email</label>
            <input type="text" name="email" id="email"/>
        </div>
        <div>
            <label for="username">Username</label>
            <input type="text" name="username" id="username"/>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" name="password" id="password"/>
        </div>
        <button type="submit">Register</button>
    </form>
</div>

</body>
</html>
