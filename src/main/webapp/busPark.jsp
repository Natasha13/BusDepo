
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Bus Park</title>
</head>
<body>
<form action="busPark" method="post" >
    <h2> Bus ID: <input type="text" name="bus_id" value=" "> <br></h2>
    <h2> User ID: <input type="text" name="user_id" value=" "><br></h2>
    <h2> Route ID: <input type="text" name="route_id" value=" "> <br></h2>
    <input type="submit" value="Execute"> <br>
</form>
<form action="busParkDelete" method="post">
    <h2> Bus ID: <input type="text" name="busPark_id" value=" "> <br></h2>
    <input type="submit" value="Delete"><br>
</form></td>
<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Bus ID</th>
        <th>BusNumber</th>
        <th>User ID</th>
        <th>User Name</th>
        <th>Route ID</th>
        <th>Route Name</th>
        <th>Accepted</th>
    </tr>

    <c:forEach var = "busPark1" items = "${busParks1}">
        <tr>
            <td> <c:out value = "${busPark1.id}"/></td>
            <td> <c:out value = "${busPark1.bus.id}"/></td>
            <td> <c:out value = "${busPark1.bus.busNumber}"/></td>
            <td> <c:out value = "${busPark1.user.id}"/></td>
            <td> <c:out value = "${busPark1.user.user_name}"/></td>
            <td> <c:out value = "${busPark1.route.id}"/></td>
            <td> <c:out value = "${busPark1.route.routeName}"/></td>
            <td> <c:out value = "${busPark1.accepted}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

