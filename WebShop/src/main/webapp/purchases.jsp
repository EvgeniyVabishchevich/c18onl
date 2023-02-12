<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<c:if test="${sessionScope.get('purchases') == null}">
    Your shopping cart is empty
</c:if>
</body>
</html>
