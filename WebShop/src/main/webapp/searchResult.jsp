<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search result</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <c:forEach var="product" items="${products}">
        <img class="img-fluid" src="${contextPath}/eshop?command=image&image=${product.getImageName()}"><br>
        Model : ${product.getName()}<br>
        Description : ${product.getDescription()}<br>
        Price : ${product.getPrice()} $<br>
        <hr>
    </c:forEach>
</body>
</html>
