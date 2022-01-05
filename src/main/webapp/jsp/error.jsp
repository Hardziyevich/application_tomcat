<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<span style="color: red">Something wrong!</span>
    <c:if test="${sessionScope.exception}">
        <span style="color: red">${sessionScope.exception}</span>
    </c:if>
</body>
</html>
