<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="message" class="com.hardziyevich.application.validator.Test" scope="request"/>

<form action="registration" method="post">
    <input type="hidden" name="command" value="registration" />
    <label for="name">First name:
        <input type="text" name="first_name" id="name">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.mapValidUser.containsKey('invalid.first.name')}">
                <span style="color: red">${sessionScope.mapValidUser.get('invalid.first.name')}</span>
            </c:if>
        </c:if>
    </label><br>
    <label for="last">Last name:
        <input type="text" name="last_name" id="last">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.mapValidUser.containsKey('invalid.last.name')}">
                <span style="color: red">${sessionScope.mapValidUser.get('invalid.last.name')}</span>
            </c:if>
        </c:if>
    </label><br>
    <label for="login">Email:
        <input type="text" name="login" id="login"">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.mapValidUser.containsKey('invalid.email')}">
                <span style="color: red">${sessionScope.mapValidUser.get('invalid.email')}</span>
            </c:if>
        </c:if>
    </label><br>
    <label for="name">Password:
        <input type="password" name="password" id="password">
    </label><br>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select><br>
    <button type="submit">registration</button>

</form>
</body>
</html>
