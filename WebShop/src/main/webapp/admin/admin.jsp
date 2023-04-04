<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin tools</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script>
        function hide(formId) {
            const form = document.getElementById(formId);
            if (form.style.display == 'none') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }
    </script>
    <jsp:include page="../scriptsLinks.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

Welcome, admin <br>

<button onclick=hide("categoryForm")>Add category</button>
<form id="categoryForm" enctype="multipart/form-data" style="display: none">
    <label for="categoryName">Name:</label>
    <input type="text" id="categoryName" name="name"/><br>

    <label for="categoryImageName">Image name:</label>
    <input type="text" id="categoryImageName" name="imageName"/><br>

    <label for="categoryImage">Image:</label>
    <input id="categoryImage" name="image" type="file" accept="image/*"><br>

    <button type="submit">Add</button>
</form>

<br>

<button onclick=hide("productForm")>Add product</button>
<form id="productForm" enctype="multipart/form-data" style="display: none">
    <label for="categorySelection">Category:</label>
    <select name="category" id="categorySelection" form="productForm">
        <c:forEach var="category" items="${categories}">
            <option value="${category.getName()}">${category.getName()}</option>
        </c:forEach>
    </select><br>

    <label for="productName">Name:</label>
    <input id="productName" name="name" type="text"/><br>

    <label for="productImageName">Image name:</label>
    <input id="productImageName" name="imageName" type="text"/><br>

    <label for="productImage">Image:</label>
    <input id="productImage" name="image" type="file" accept="image/*">

    <label for="price">Price:</label>
    <input id="price" name="price" type="text"/><br>

    <textarea id="description" name="description" rows="5" cols="50" placeholder="Details..."></textarea><br>

    <button type="submit">Add</button>
</form>

<script>
    $('#categoryForm').on('submit', (function (e) {
        e.preventDefault();

        $.ajax({
            url: '/eshop?command=new-category',
            type: 'post',
            data: new FormData(this),
            cache: false,
            contentType: false,
            processData: false,
            success: function () {
                alert("Category added")
            },
            error: function (data) {
                console.log(data)
            }
        })
    }));

    $('#productForm').on('submit', (function (e) {
        e.preventDefault();

        $.ajax({
            url: '/admin/add-product',
            type: 'post',
            data: new FormData(this),
            cache: false,
            contentType: false,
            processData: false,
            success: function () {
                alert("Product added")
            },
            error: function (data) {
                console.log(data)
            }
        })
    }));
</script>
</body>
</html>