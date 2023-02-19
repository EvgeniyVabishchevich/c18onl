<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script type="text/javascript">
        function addProductId(productId) {
            $.ajax
            ({
                url: '/add-product',
                data: {productId: productId},
                type: 'post',
                success: function () {
                    alert('product added');
                }
            })
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${products.size() == 0}">
    Products list is empty now. Sorry.
</c:if>
<c:if test="${products.size() > 0}">
    <h1>${categoryName}</h1>
    <c:forEach var="product" items="${products}">
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
                <button type="button" class="btn" value="${product.getId()}" onclick="addProductId(this.value)">Add to
                    basket
                </button>
            </div>
        </div>
    </c:forEach>
</c:if>
</body>
</html>
