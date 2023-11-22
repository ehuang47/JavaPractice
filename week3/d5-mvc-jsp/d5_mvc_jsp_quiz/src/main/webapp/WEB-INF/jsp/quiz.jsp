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
    <title>Quiz</title>
    <%@include file="partials/header.jsp"%>
</head>
<body>
<form method="post" action="/quiz">
    <p>${question.description}</p>

    <c:forEach var="choice" items="${question.choices}" varStatus="loop">
        <input type="radio" name="selectedChoiceId" value="${choice.id}">${choice.description}<br>
    </c:forEach>

    <button type="submit">Submit</button>
</form>
</body>
</html>
