<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <jsp:include page="scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<label>Name : ${user.getName()}</label><br>
<label>Surname : ${user.getSurname()}</label><br>
<label>Email : ${user.getEmail()}</label><br>
<label>Birthday : ${user.getBirthday()}</label><br>

<c:if test="${sessionScope.get(\"user\").getUserType().name()==\"ADMIN\"}">
    <label>You have admin privileges, press link to check them.</label><br>
    <a href="${pageContext.request.contextPath}/categories?page=admin/admin">Admin tools</a><br>
</c:if>

<hr>
<c:set var="orders" value="${orders}" scope="request"/>
<c:forEach var="order" items="${orders}">
    <label>Order №${order.getId()} / Date : ${order.getDate()}. </label><br>
    <c:forEach var="product" items="${order.getProducts().keySet()}">
        <br>
        <label>Product ${product.getName()}</label><br>
        <label>Description : ${product.getDescription()}</label><br>
        <label>Price : ${product.getPrice()}</label><br>
        <label>Amount : ${order.getProducts().get(product)}</label><br>
    </c:forEach>
    <hr>
</c:forEach>

</body>
</html>
