<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/routes.css">
</head>
<body>
<div id="content">
    <div id="article">
        <ul class="widget-list">
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('admin')}">
                    <li><a href="/busPark">Home page</a></li>
                    <li><a href="/routes">Routes</a></li>
                    <li><a href="/users">Drivers</a></li>
                    <li><a href="/buses">Buses</a></li>
                    <li><a href='/logout.jsp'>Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/busPark">Home page</a></li>
                    <li><a href='/logout.jsp'>Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

</body>
</html>
