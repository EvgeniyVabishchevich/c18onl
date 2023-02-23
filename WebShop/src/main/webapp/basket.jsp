<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="scriptsLinks.jsp"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

    <script>
        function removeProduct(productId, elementId) {
            $.ajax({
                url: '/remove-product',
                data: {'productId': productId},
                type: 'post',
                success: recalculateDisplayedInfo(elementId)
            })
        }

        function recalculateDisplayedInfo(elementId) {
            let amount = parseInt(document.getElementById("count_" + elementId).textContent) - 1;
            let price = parseInt(document.getElementById("total_" + elementId).textContent) / (amount + 1);
            if (amount == 0) {
                document.getElementById("row_" + elementId).style.display = "none";
            }
            recalculateAmount(elementId, amount);
            recalculateTotal(elementId, price);
            recalculateTotalAll(price);
        }

        function recalculateAmount(labelId, amount) {
            document.getElementById("count_" + labelId).textContent = (amount).toString();
        }

        function recalculateTotal(totalId, removedPrice) {
            let newTotal = parseInt(document.getElementById("total_" + totalId).textContent) - removedPrice;
            document.getElementById("total_" + totalId).textContent = newTotal.toString();
        }

        function recalculateTotalAll(removedPrice) {
            document.getElementById("totalAll").textContent =
                (parseInt(document.getElementById("totalAll").textContent) - removedPrice).toString();
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:set var="total" value="0"/>
<c:if test="${productsMap.size() == 0}">
    Products list is empty now. Sorry.
</c:if>
<c:if test="${productsMap.size() > 0}">
    <c:forEach var="product" items="${productsMap.keySet()}" varStatus="loop">
        <c:set var="total" value="${total + product.getPrice() * productsMap.get(product)}"/>
        <div class="row mb-3" id="row_${loop.count}">
            <div class="col">
                <div class="container-sm">
                    <img class="img-fluid" src="images/${product.getImageName()}">
                </div>
            </div>
            <div class="col-10">
                Model : ${product.getName()}<br>
                Description : ${product.getDescription()}<br>
                Price : ${product.getPrice()} $<br>
                Amount : <label id="count_${loop.count}">${productsMap.get(product)}</label><br>
                Total price : <label id="total_${loop.count}">${product.getPrice() * productsMap.get(product)}</label> $<br>
                <button type="button" class="btn btn-danger" value="${product.getId()}"
                        onclick="removeProduct(this.value, ${loop.count})">Remove
                </button>
            </div>
        </div>
    </c:forEach>
    <div class="row">
        <div class="col">
            Total price for all selected products : <label id="totalAll">${total}</label> $.
        </div>
    </div>
</c:if>
</body>
</html>
