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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/products.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="row">
    <c:forEach items="${sessionScope.get('categories')}" var="category">
        <div class="card w-25">
            <div class="card-body">
                <a href="FindProductsInfoServlet?category=${category.getName()}"><img class="img-fluid" src="images/${category.getImageName()}"></a>
                <a href="FindProductsInfoServlet?category=${category.getName()}">${category.getName()}</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>

