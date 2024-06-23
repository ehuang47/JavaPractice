<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 6/22/24
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <%@include file="partials/header.jsp" %>
</head>
<body>
<h1>Welcome to the home page!</h1>
<h2>Quiz Categories</h2>
<table>
    <thead>
    <tr>
        <th>Category</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quiz" items="${quizList}" varStatus="loop">
        <tr>
            <td><a href="/quiz/${quiz.id}">${quiz.category}</a></td>
            <td>Multiple Choice</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Recent Quizzes</h2>
<table>
    <thead>
    <tr>
        <th>Type</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quizResult" items="${quizResultList}" varStatus="loop">
        <tr>
            <td>
                <a href="/quiz-result/${quizResult.id}">${quizIdToCategory.get(quizResult.quizId)}</a>
            </td>
            <td>${quizResult.dateStarted}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
