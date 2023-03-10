<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

Hi, ${sessionScope.get("user").getName()}

<c:if test="${sessionScope.get(\"user\").getUserType().name()==\"admin\"}">
    <br><label>You have admin privileges, press here to check them.</label>
    <br><a href="admin/admin.jsp">Admin tools</a>
</c:if>
</body>
</html>
