<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Route Name</th>
    </tr>

    <c:forEach var = "route1" items = "${routes1}">
        <tr>
            <td> <c:out value = "${route1.id}"/></td>
            <td> <c:out value = "${route1.routeName}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
