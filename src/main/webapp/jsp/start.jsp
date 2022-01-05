<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        .container {
            text-align: center;
            color: chartreuse;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<c:url value="/command?command=go_start" var="var"/>
<div class="container">
    <form action="command?command=start" method="post" name="command" value="start">
        <h1><fmt:message key="page.start.titel"/></h1>
        <a href="${pageContext.request.contextPath}/command?command=go_registration">
            <button type="button"><fmt:message key="page.registration.button"/></button></a>
        <a href="${pageContext.request.contextPath}/command?command=go_login">
            <button type="button"><fmt:message key="page.login.button"/></button>
        </a><br/>
    </form>
</div>
</body>
</html>
