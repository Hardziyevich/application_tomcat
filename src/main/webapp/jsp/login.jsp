<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<%@include file="header.jsp" %>
<c:url value="/command?command=go_login" var="var"/>
<form action="command?command=login" method="post">
    <input type="hidden" name="command" value="login"/>
    <label for="login">Email:
        <input type="text" name="login" id="login" value="${sessionScope.email}" required>
    </label><br>
    <label for="password">Password:
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit">sign in</button>
    <a href="${pageContext.request.contextPath}/command?command=go_registration">
        <button type="button">registration</button>
    </a>
    <c:if test="${param.error != null}">
        <div>
            <span style="color: red">Email or password is not correct.</span>
        </div>
    </c:if>
</form>
</body>
</html>
