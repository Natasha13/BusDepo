
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<head>
    <title>User</title>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Logine</th>
        <th>Password</th>
        <th>Spesiality</th>
    </tr>

    <c:forEach var = "user1" items = "${users1}">
        <tr>
            <td> <c:out value = "${user1.id}"/></td>
            <td> <c:out value = "${user1.user_name}"/></td>
            <td> <c:out value = "${user1.login}"/></td>
            <td> <c:out value = "${user1.password}"/></td>
            <td> <c:out value = "${user1.user_spesiality}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
