<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container-fluid">
    <div class="row bg-dark">
        <div class="col">
            <a class="btn btn-success" href="${contextPath}/eshop?command=categories&page=categories.jsp">Main page</a>
        </div>
        <div class="col d-flex flex-row-reverse">
            <a class="btn btn-warning" href="${contextPath}/eshop?command=cart">Purchase</a>
            <a class="btn btn-info" href="${contextPath}/eshop?command=user">User</a>
            <a class="btn btn-info" href="${contextPath}/search.jsp">Search</a>
        </div>
    </div>
</div>
