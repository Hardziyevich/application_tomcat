<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@include file="header.jsp" %>
<c:url value="/command?command=go_registration" var="var"/>
<form action="command?command=registration" method="post">
    <input type="hidden" name="command" value="registration"/>
    <label for="name"><fmt:message key="page.registration.firstname"/>:
        <input type="text" name="first_name" id="name" value="${sessionScope.param.firstName}">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.validUser.contains('invalid.first.name')}">
                <span style="color: red"><fmt:message key="page.registration.valid.firstname"/></span>
            </c:if>
        </c:if>
    </label><br>
    <label for="last"><fmt:message key="page.registration.lastname"/>:
        <input type="text" name="last_name" id="last" value="${sessionScope.param.lastName}">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.validUser.contains('invalid.last.name')}">
                <span style="color: red"><fmt:message key="page.registration.valid.lastname"/></span>
            </c:if>
        </c:if>
    </label><br>
    <label for="login"><fmt:message key="page.registration.email"/>:
        <input type="text" name="login" id="login" value="${sessionScope.param.email}">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.validUser.contains('invalid.email')}">
                <span style="color: red"><fmt:message key="page.registration.valid.email"/></span>
            </c:if>
            <c:if test="${sessionScope.validUser.contains('invalid.user')}">
                <span style="color: red"><fmt:message key="page.registration.valid.user.exist"/></span>
            </c:if>
        </c:if>
    </label><br>
    <label for="password"><fmt:message key="page.registration.password"/>:
        <input type="password" name="password" id="password" value="${sessionScope.param.password}">
        <c:if test="${param.error != null}">
            <c:if test="${sessionScope.validUser.contains('invalid.password')}">
                <span style="color: red"><fmt:message key="page.registration.valid.password"/></span>
            </c:if>
        </c:if>
    </label><br>
    <label for="role"><fmt:message key="page.registration.role"/>:
        <select name="role" id="role" >
            <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role}">${role}</option>
            </c:forEach>
        </select><br>
    </label>
    <button type="submit"><fmt:message key="page.registration.button"/></button>
</form>
</body>
</html>
