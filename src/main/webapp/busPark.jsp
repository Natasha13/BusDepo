
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Bus Park</title>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Bus ID</th>
        <th>busPark ID</th>
        <th>Route ID</th>
        <th>Accepted</th>
    </tr>

    <c:forEach var = "busPark1" items = "${busParks1}">
        <tr>
            <td> <c:out value = "${busPark1.id}"/></td>
            <td> <c:out value = "${busPark1.bus_id}"/></td>
            <td> <c:out value = "${busPark1.user_id}"/></td>
            <td> <c:out value = "${busPark1.route_id}"/></td>
            <td> <c:out value = "${busPark1.accepted}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

