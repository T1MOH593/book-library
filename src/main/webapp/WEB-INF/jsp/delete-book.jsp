
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>
    <%@ include file="layout.jsp"%>
    <main class="container p-5">
        <form action="${pageContext.request.contextPath}/delete-book" method="post">
            <div class="form-group">
                <select class="form-control" name="titleAndAuthor">
                    <c:forEach var="bookEntity" items="${requestScope.myBooks}">
                        <option value="${bookEntity.get("title")}delimiter${bookEntity.get("author")}">"${bookEntity.get("title")}", ${bookEntity.get("author")}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Delete</button>
        </form>
    </main>
</body>
</html>
