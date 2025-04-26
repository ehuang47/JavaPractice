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
    <title>Question Management</title>
    <% request.setAttribute("userRole", 1); %>
    <%@include file="../partials/header.jsp" %>
</head>
<body>
<h1>Question Management</h1>

<h2>Questions</h2>
<table id="question-management-table">
    <button>
        <a href="/question">Add Question</a>
    </button>
    <thead>
    <tr>
        <th>Correct Choice Id</th>
        <th>Description</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="question" items="${questionList}" varStatus="loop">
        <tr>
            <td>${question.correctChoiceId}</td>
            <td>${question.description}</td>
            <td>${question.active}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

<script>
  // const questionManagementTable = document.getElementById("question-management-table");
  // questionManagementTable.addEventListener('click', async (e) => {
  //   if (e.target instanceof HTMLButtonElement) {
  //     const id = e.target.id.split('-').at(-1);
  //     const res = await fetch('/question/management', {
  //       method: 'PATCH',
  //       headers: { 'Content-Type': 'application/json',  'Accept': 'application/json'},
  //       body: JSON.stringify({
  //         id,
  //         active: e.target.value === 'true' ? 'false' : 'true'
  //       } )
  //     })
  //     const body = await res.json();
  //     if (body.reload) {
  //       window.location.reload();
  //     }
  //   }
  // })
</script>
