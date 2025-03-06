<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 2/17/25
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Result Management</title>
    <% request.setAttribute("userRole", 1); %>
    <%@include file="../partials/header.jsp" %>
</head>
<body>
<h1>Quiz Result Management</h1>

<h2>Filters</h2>
<form method="get" action="${pageContext.request.contextPath}/quiz-result/management">
    <div>
        <label for="columnToOrder">Sort</label>
        <select name="columnToOrder" id="columnToOrder">
            <option value="name">Name</option>
            <option value="date_submitted">Date</option>
        </select>
    </div>
    <div>
        <label for="ascending">Sort Order</label>
        <select name="ascending" id="ascending">
            <option value="ASC">Ascending</option>
            <option value="DESC">Descending</option>
        </select>
    </div>
    <div>
        <label for="columnToFilter">Filter</label>
        <select name="columnToFilter" id="columnToFilter">
            <option value="category">Category</option>
            <option value="user_id">User</option>
        </select>
    </div>
    <div>
        <label for="filterValue">Filter Value</label>
        <input type="text" name="filterValue" id="filterValue"/>
    </div>
    <button type="submit">Search</button>
</form>

<h2>Quiz Results</h2>
<table id="quiz-result-management-table">
    <thead>
    <tr>
        <th>Taken Time</th>
        <th>Category</th>
        <th>User Full Name</th>
        <th>Question Count</th>
        <th>Score</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quizResult" items="${quizResults}" varStatus="loop">
        <tr>
            <td>${quizResult.dateSubmitted}</td>
            <td>${quizResult.category}</td>
            <td>${quizResult.firstName} ${quizResult.lastName}</td>
            <td>10</td>
            <td>${quizResult.score}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
