<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Error 404</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>

<body>
<div class="d-flex align-items-center justify-content-center vh-100">
    <div class="text-center">
        <h1 class="display-1 fw-bold">404</h1>
        <c:if test="${errorMsg != null}">
            <p class="fs-3"><span class="text-danger">Opps!</span> ${errorMsg}</p>
        </c:if>
        <c:if test="${errorMsg == null}">
            <p class="fs-3"><span class="text-danger">Opps!</span> Page not found.</p>
            <p class="lead">
                The page you’re looking for doesn’t exist.
            </p>
        </c:if>
        <a href="/eshop?command=categories&page=categories.jsp" class="btn btn-primary">Go Home</a>
    </div>
</div>
</body>

</html>