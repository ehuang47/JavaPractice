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
    <title>About Me</title>
    <%@include file="partials/header.jsp"%>
</head>
<body>
<h1>About</h1>
<p>
    Server Version: <%= application.getServerInfo() %><br>
    Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %> <br>
    JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> <br>
</p>
<p>Interests: ${interests}</p>
</body>
</html>
