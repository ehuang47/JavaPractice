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
<h3>Submitted by: firstname lastname</h3>
<h3>Start: ${startTime}</h3>
<h3>End: ${endTime}</h3>
<h3>Duration: ${quizDurationMinutes}min ${quizDurationSeconds}sec</h3>
<h3>Result: ${result}</h3>
<a href="/home">
    <button>Take Another Quiz</button>
</a>

<ol>
    <c:forEach var="question" items="${questionList}" varStatus="loop">
        <li>${question.description}</li>
        <c:forEach var="choice" items="${question.choiceList}"
                   varStatus="loop">
            <c:set var="selected"
                   value="${choice.id == questionIdToSelectedChoiceId.get(question.id)}"/>
            <c:set var="choiceBackgroundColor"
                   value="background-color: ${choice.id == question.correctChoiceId ? 'green' : 'red'}"/>

            <input id="choice-${choice.id}" type="radio"
                   name="selected-choice-question-${question.id}"
                   value="${choice.id}"
                ${selected ? 'checked' : ''}
                   disabled/>
            <label for="choice-${choice.id}"
                   style="${selected ? choiceBackgroundColor : ""}">
                    ${choice.description}
            </label>
            <br>
        </c:forEach>
    </c:forEach>
</ol>

</body>
</html>
