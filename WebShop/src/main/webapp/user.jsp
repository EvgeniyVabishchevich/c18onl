<%--
  Created by IntelliJ IDEA.
  User: Женя
  Date: 12.02.2023
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="scripts-links.jsp"/>
<jsp:include page="header.jsp"/>

Hi, ${sessionScope.get("login")}
</body>
</html>
