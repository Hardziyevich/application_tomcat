
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<%--<c:url value="/command?command=start_page" var="var"/>--%>
<form action="start.jsp" method="post" name="command" value="start>
    <a href="${pageContext.request.contextPath}/login">
        <button type="button">login</button>
    </a></br>
    <a href="${pageContext.request.contextPath}/command?command=go_registration">
        <button type="button">registration</button>
    </a>
    <h1>
        <div style="text-align: center; color: chartreuse">Electronic element store!</div>
    </h1>
</form>
</body>
</html>
