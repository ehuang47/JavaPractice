<%--
  Created by IntelliJ IDEA.
  User: ethan
  Date: 2/12/25
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <% request.setAttribute("userRole", 1); %>
    <%@include file="../partials/header.jsp" %>
</head>
<body>
<h1>User Management</h1>
<table id="user-management-table">
    <thead>
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Status</th>
        <th>Role</th>
        <th>Toggle Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}" varStatus="loop">
        <tr>
            <td>${user.firstName} ${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.active ? "Active" : "Suspended"}</td>
            <td>${user.role == 0? "User" : "Admin"}</td>
            <td>
                <button id="toggle-active-btn-${user.id}" value="${user.active}">${user.active ? "Suspend" : "Activate"}</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

<script>
    const userManagementTable = document.getElementById("user-management-table");
    userManagementTable.addEventListener('click', async (e) => {
      if (e.target instanceof HTMLButtonElement) {
        console.log(e.target, e.target.value);
        const res = await fetch('/user/management', {
          method: 'PATCH',
          headers: { 'Content-Type':'application/x-www-form-urlencoded' },
          body: {
            active: !Boolean(e.target.value)
          }
        })
        console.log(res);
      }
    })

</script>