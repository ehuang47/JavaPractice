<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 11/10/23
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<nav style="display: flex; border-bottom: 1px solid black; padding: 4px; width: 100%; justify-content: space-around">
    <c:if test="${userRole == 0}">
        <div>
            <a href="/home">
                <button>Home</button>
            </a>
        </div>
    </c:if>

    <c:if test="${userRole != 1}">
        <div>
            <a href="/contact-us">
                <button>Contact Us</button>
            </a>
        </div>
    </c:if>

    <c:if test="${userRole == null}">
        <div>
            <a href="/register">
                <button>Register</button>
            </a>
        </div>

        <div>
            <a href="/login">
                <button>Login</button>
            </a>
        </div>
    </c:if>

    <c:if test="${userRole != null}">
        <div>
            <a href="/logout">
                <button>Logout</button>
            </a>
        </div>
    </c:if>
</nav>
</body>
</html>
