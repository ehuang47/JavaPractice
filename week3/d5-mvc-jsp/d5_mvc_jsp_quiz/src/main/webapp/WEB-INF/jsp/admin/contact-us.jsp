<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 2/12/25
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact Us Management</title>
    <% request.setAttribute("userRole", 1); %>
    <%@include file="../partials/header.jsp" %>
</head>
<body>
<h1>Contact Us Management</h1>
<h2>Here's a list of submitted messages.</h2>
<table>
    <thead>
    <tr>
        <th>Email</th>
        <th>Submitted</th>
        <th>Subject</th>
        <th>Message</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="message" items="${messageList}" varStatus="loop">
        <tr>
            <td>${message.email}</td>
            <td>${message.dateSubmitted}</td>
            <td>${message.subject}</td>
            <td>${message.message}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

<style>
    table {
        border-collapse: collapse;
    }
    td, th {
        border: 1px solid black;
        padding: 4px;
    }
</style>

