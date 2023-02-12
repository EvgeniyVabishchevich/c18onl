<%--
  Created by IntelliJ IDEA.
  User: Женя
  Date: 11.02.2023
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="scripts-links.jsp"/>
<jsp:include page="header.jsp"/>

<div class="row">
    <c:forEach items="${sessionScope.get('categories')}" var="category">
        <div class="card w-25">
            <div class="card-body">
                <a href="/products?categoryName=${category.getName()}"><img class="img-fluid" src="images/${category.getImageName()}"></a>
                <h5 class="card-title">${category.getName()}</h5>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

