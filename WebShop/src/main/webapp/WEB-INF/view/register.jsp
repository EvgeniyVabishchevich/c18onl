<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>Registration</title>
    <jsp:include page="scriptsLinks.jsp"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<form method="post" action="${contextPath}/register">
    <div class="mb-3">
        <label for="login" class="form-label">Login</label>
        <input name="login" type="text" class="form-control" id="login">
    </div>
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input name="name" type="text" class="form-control" id="name">
    </div>
    <div class="mb-3">
        <label for="surname" class="form-label">Surname</label>
        <input name="surname" type="text" class="form-control" id="surname">
    </div>
    <div class="mb-3">
        <label for="birthday" class="form-label">Date of birthday</label>
        <input name="birthday" type="date" class="form-control" id="birthday">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input name="email" type="text" class="form-control" id="email">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input name="password" type="password" class="form-control" id="password">
    </div>
    <div class="mb-3">
        <label for="passwordRepeat" class="form-label">Repeat password</label>
        <input name="passwordRepeat" type="password" class="form-control" id="passwordRepeat">
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form>

<br>${mistake}

</body>
</html>
