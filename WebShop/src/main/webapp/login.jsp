<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="scriptsLinks.jsp"/>
</head>

<body>
<jsp:include page="header.jsp"/>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="mb-3">
        <label for="login" class="form-label">Login</label>
        <input name="login" type="text" class="form-control" id="login" value="admin">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input name="password" type="password" class="form-control" id="password" value="admin">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
Don't have account? <a href="newAccount.jsp">Click</a>

</body>

</html>