<%--
  Created by IntelliJ IDEA.
  User: Женя
  Date: 12.02.2023
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.get('purchases') == null}">
    Your shopping cart is empty
</c:if>
</body>
</html>
