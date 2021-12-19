<%--
  Created by IntelliJ IDEA.
  User: pasha
  Date: 19.12.2021
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login" method="post">
        <label for="email">Email:
            <input type="text" name="login" id="email" value="${param.email}" required>
        </label><br>
        <label for="password">Password:
            <input type="password" name="password" id="password" required>
        </label><br>
        <button type="submit">sign in</button>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">registration</button>
        </a>
        <c:if test="${param.error != null}">
            <div>
                <span>Email or password is not correct.</span>
            </div>
        </c:if>
    </form>
</body>
</html>
