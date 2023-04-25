<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container-fluid">
    <div class="row bg-dark">
        <div class="col">
            <a class="btn btn-success" href="${contextPath}/categories?page=categories">Main page</a>
        </div>
        <div class="col d-flex flex-row-reverse">
            <a class="btn btn-warning" href="${contextPath}/cart">Purchase</a>
            <a class="btn btn-info" href="${contextPath}/user">User</a>
            <a class="btn btn-info" href="${contextPath}/categories?page=search">Search</a>
        </div>
    </div>
</div>
