
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Bus Park</title>
</head>
<body>
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <form autocomplete="off" action="busPark" method="post" >
        <h2> Bus ID: <input type="text" name="bus_id" value=" "> <br></h2>
        <h2> User ID: <input type="text" name="user_id" value=" "><br></h2>
        <h2> Route ID: <input type="text" name="route_id" value=" "> <br></h2>
        <input type="submit" value="Execute"> <br>
    </form>
    <form autocomplete="off" action="busParkDelete" method="post">
        <h2> Bus ID: <input type="text" name="busPark_id" value=" "> <br></h2>
        <input type="submit" value="Delete"><br>
    </form>
</c:if>


<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Route ID</th>
        <th>Route Name</th>
        <th>Bus ID</th>
        <th>BusNumber</th>
        <th>User ID</th>
        <th>User Name</th>
        <th>Accepted</th>
    </tr>

    <c:forEach var = "busPark" items = "${busParks}">
        <tr>
            <td> <c:out value = "${busPark.id}"/></td>
            <td> <c:out value = "${busPark.route.id}"/></td>
            <td> <c:out value = "${busPark.route.routeName}"/></td>
            <td> <c:out value = "${busPark.bus.id}"/></td>
            <td> <c:out value = "${busPark.bus.busNumber}"/></td>
            <td> <c:out value = "${busPark.user.id}"/></td>
            <td> <c:out value = "${busPark.user.user_name}"/></td>
            <td> <c:out value = "${busPark.accepted}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

