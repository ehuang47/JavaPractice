<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 11/10/23
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contact Us</title>
<%--    conditionally show home/login/register if logged in --%>
    <% request.setAttribute("showLogout", false); %>
    <% request.setAttribute("showLogin", true); %>
    <% request.setAttribute("showHome", true); %>
    <% request.setAttribute("showRegister", true); %>
    <% request.setAttribute("showContactUs", false); %>
    <%@include file="partials/header.jsp" %>
</head>
<body>
<h1>Contact Us</h1>
<form method="post" action="${pageContext.request.contextPath}/contact-us">
    <div>
        <label for="subject">Subject</label>
        <input type="text" name="subject" id="subject"/>
    </div>
    <div>
        <label for="email">Email</label>
        <input type="text" name="email" id="email"/>
    </div>
    <div>
        <label for="message">Message</label>
        <textarea name="message" id="message"></textarea>
    </div>
    <button type="submit">Send Message</button>
</form>
</body>
</html>
