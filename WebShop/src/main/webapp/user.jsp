<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

Hi, ${sessionScope.get("user_type")}
</body>
</html>
