<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="row">
    <c:forEach items="${sessionScope.get('categories')}" var="category">
        <div class="card w-25">
            <div class="card-body">
                <a href="/products?categoryName=${category.getName()}">
                    <img class="img-fluid" src="${pageContext.request.contextPath}/images/${category.getImageName()}">
                </a>
                <h5 class="card-title">${category.getName()}</h5>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

