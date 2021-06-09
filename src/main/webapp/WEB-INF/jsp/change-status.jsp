
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change status</title>
</head>
<body>
    <%@ include file="layout.jsp"%>
    <main class="container p-5">
        <form action="${pageContext.request.contextPath}/change-status" method="post">
            <div class="form-group">
                <select class="form-control" name="status">
                    <option value="WANT_TO_READ">WANT_TO_READ</option>
                    <option value="ALREADY_READ">ALREADY_READ</option>
                    <option value="READING">READING</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" name="titleAndAuthor">
                    <c:forEach var="bookEntity" items="${requestScope.myBooks}">
                        <option value="${bookEntity.get("title")}delimiter${bookEntity.get("author")}">"${bookEntity.get("title")}", ${bookEntity.get("author")}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Change</button>
        </form>
    </main>
</body>
</html>
