<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Categories</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row">
    <c:forEach items="${categories}" var="category">
        <div class="card w-25">
            <div class="card-body">
                <a href="${contextPath}/eshop?command=products&categoryName=${category.getName()}">
                    <img class="img-fluid" src="${contextPath}/eshop?command=image&image=${category.getImageName()}">
                </a>
                <h5 class="card-title">${category.getName()}</h5>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

