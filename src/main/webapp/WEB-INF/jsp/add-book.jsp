
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
    <%@ include file="layout.jsp"%>
    <main class="container p-5">
        <form action="${pageContext.request.contextPath}/add-book" method="post">
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" name="title" placeholder="title" type="text">
            </div>
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" name="author" placeholder="Author" type="text">
            </div>
            <div class="form-group">
                <select class="form-control" name="status">
                    <option value="WANT_TO_READ">WANT_TO_READ</option>
                    <option value="ALREADY_READ">ALREADY_READ</option>
                    <option value="READING">READING</option>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Add</button>
                <c:if test="${not empty requestScope.errors}">
                    <div style="color: red">
                        <c:forEach var="error" items="${requestScope.errors}">
                            <span>${error.errorMessage}</span>
                            <br>
                        </c:forEach>
                    </div>
                </c:if>
        </form>
    </main>
</body>
</html>
