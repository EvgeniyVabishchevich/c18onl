<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script>
        function removeProduct(productId) {
            $.ajax({
                url: '/remove-product',
                data: {'productId': productId},
                type: 'post'
            })
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${productsMap.size() == 0}">
    Products list is empty now. Sorry.
</c:if>
<c:if test="${productsMap.size() > 0}">
    <h1>${categoryName}</h1>
    <c:forEach var="product" items="${productsMap.keySet()}">
        <div class="row">
            <div class="col">
                <div class="container-sm">
                    <img class="img-fluid" src="images/${product.getImageName()}">
                </div>
            </div>
            <div class="col-10">
                Model : ${product.getName()}<br>
                Description : ${product.getDescription()}<br>
                Price : ${product.getPrice()} $<br>
                Amount : ${productsMap.get(product)}<br>
                Total price : ${product.getPrice() * productsMap.get(product)} $<br>
                <button type="button" class="btn btn-danger" value="${product.getId()}"
                        onclick="removeProduct(this.value)">Remove
                </button>
            </div>
        </div>
    </c:forEach>
</c:if>
</body>
</html>
