<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 11/12/23
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%--    <title>${quiz.category} Quiz</title>--%>
    <%@include file="partials/header.jsp"%>
</head>
<body>
<%--<h1>${quiz.category} Quiz</h1>--%>
<form method="post" action="/quiz">
<%--    <ol>--%>
<%--        <c:forEach var="question" items="${quiz.questionList}" varStatus="loop">--%>
<%--            <li>${question.description}</li>--%>
<%--            <c:forEach var="choice" items="${question.choiceList}" varStatus="loop">--%>
<%--                <input type="radio" name="selectedChoiceId" value="${choice.id}">${choice.description}<br>--%>
<%--            </c:forEach>--%>
<%--        </c:forEach>--%>
<%--    </ol>--%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
