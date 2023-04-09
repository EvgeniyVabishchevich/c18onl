<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Search</title>
    <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <script>
        $(document).ready(function () {
            $('#filterForm').on('submit', (function (e) {
                e.preventDefault();

                const formData = new FormData(this);

                $.ajax({
                    url: '/eshop?command=search',
                    type: 'post',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        $('#searchResult').html(data);
                    }
                })
            }));
        })
    </script>
</head>
<body>
<jsp:include page="scriptsLinks.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<form id="filterForm" method="post">
    <div style="text-align:center">
        <input name="searchRequest" form="filterForm" placeholder="Search" type="text">
        <button type="submit" id="searchButton">Search</button>
    </div>

    <label>Filter</label><br>
    <label for="categorySelection">Category:</label>
    <select name="category" id="categorySelection" form="filterForm">
        <option value="All">All</option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.getName()}">${category.getName()}</option>
        </c:forEach>
    </select><br>
    <label>Price</label>
    <input name="fromPrice" type="number" placeholder="From"/>
    <input name="toPrice" type="number" placeholder="To"/><br>
    <button type="submit">Apply</button>
</form>

<div id="searchResult"></div>
</body>
</html>
