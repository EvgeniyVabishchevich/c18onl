<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${category.getName()} products</title>
    <jsp:include page="scriptsLinks.jsp"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script type="text/javascript">
        function addProductId(productId) {
            $.ajax
            ({
                url: '/add-product',
                data: {productId: productId},
                type: 'post',
                success: alert('product added')
            })
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="products" value="${category.getProductList()}"/>
<c:if test="${products.size() == 0}">
    Products list is empty now. Sorry.
</c:if>
<c:if test="${products.size() > 0}">
    <h1>${categoryName}</h1>
    <c:forEach var="product" items="${products}">
        <div class="row">
            <div class="col">
                <div class="container-sm">
                    <img class="img-fluid" src="${contextPath}/images/${product.getImageName()}">
                </div>
            </div>
            <div class="col-10">
                Model : ${product.getName()}<br>
                Description : ${product.getDescription()}<br>
                Price : ${product.getPrice()} $<br>
                <button type="button" class="btn btn-success" value="${product.getId()}" onclick="addProductId(this.value)">
                    Add to shopping cart
                </button>
            </div>
        </div>
    </c:forEach>
</c:if>
</body>
</html>
