
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All books</title>
</head>
<body>
    <%@ include file="layout.jsp"%>
    <main class="container p-5">
        <table class="table table-striped">
            <thead>
            <th>Title</th>
            <th>Author</th>
            </thead>
            <tbody>
            <c:forEach var="bookEntity" items="${requestScope.allBooks}">
                <tr>
                    <td>${bookEntity.get("title")}</td>
                    <td>${bookEntity.get("author")}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>
