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
    <title>${quizCategory} Quiz Results</title>
    <%@include file="partials/header.jsp" %>
</head>
<body>
<h1>${quizCategory} Quiz Results</h1>
<h3>Duration: ${quizDurationMinutes}min ${quizDurationSeconds}sec</h3>

<ol>
    <c:forEach var="c" items="${savedChoiceList}" varStatus="loop">
        <c:set var="q" value="${questionIdToQuestion.get(c.questionId)}"/>
        <li style="background-color: ${q.correctChoiceId == c.choiceId ? "green":"red"}">
                ${questionIdToQuestion.get(c.questionId).description}
        </li>
    </c:forEach>
</ol>
</body>
</html>
