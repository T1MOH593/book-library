
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <%@ include file="layout.jsp"%>
    <main class="container p-5" >
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="form-group">
                <input autocomplete="off" autofocus value="${requestScope.name}" class="form-control" name="name" placeholder="name" type="text">
            </div>
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" name="email" placeholder="Email" type="text">
            </div>
            <div class="form-group">
                <input class="form-control" name="password" placeholder="Password" type="password">
            </div>
            <div style="text-align: center">
                <button class="btn btn-primary" type="submit">Register</button>
            </div>
        </form><br>
        <c:if test="${not empty requestScope.errors}">
            <c:forEach var="error" items="${requestScope.errors}">
                <div style="color: red">
                    <span>${error.errorMessage}</span><br>
                </div>
            </c:forEach>
        </c:if>
    </main>
</body>
</html>
