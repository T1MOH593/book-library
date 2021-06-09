<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1, width=device-width">

  <!-- http://getbootstrap.com/docs/4.5/ -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

  <link href="${pageContext.request.contextPath}/WEB-INF/static/styles.css" rel="stylesheet">

  <!-- http://getbootstrap.com/docs/4.5/ -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>

    <nav class="navbar navbar-expand-md navbar-light bg-light border">
      <div class="collapse navbar-collapse" id="navbar">
        <c:if test="${not empty sessionScope.email}">
            <ul class="navbar-nav mr-auto mt-2">
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/delete-book">Delete book</a></li>
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/change-status">Change status of book</a></li>
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/all-books">All books</a></li>
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/add-book">Add book</a></li>
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/my-books">My books</a></li>
            </ul>
            <ul class="navbar-nav ml-auto mt-2">
              <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Log Out</a></li>
            </ul>
        </c:if>
            <c:if test="${empty sessionScope.email}">
                <ul class="navbar-nav ml-auto mt-2">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/registration">Register</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Log In</a></li>
                </ul>
            </c:if>
      </div>
    </nav>

</body>
</html>
