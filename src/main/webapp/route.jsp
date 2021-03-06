<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form autocomplete="off"  action="routes" method="post" >
    <h2> Route name: <input type="text" name="route_name" value=""><br></h2>
    <input type="submit" value="Execute"> <br>
</form>

<form autocomplete="off" action="routeDelete" method="post">
    <h2> Route ID: <input type="text" name="route_id" value=""> <br></h2>
    <input type="submit" value="Delete"><br>
</form>

<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Route Name</th>
    </tr>

    <c:forEach var = "route" items = "${routes}">
        <tr>
            <td> <c:out value = "${route.id}"/></td>
            <td> <c:out value = "${route.routeName}"/></td>
        </tr>
    </c:forEach>
</table>

<form action="routes" method="get">
    <input type="hidden" name="page" value="${page == null ? 2 : page +1}"><br>
    <input type="submit" value="Next">
</form>

<c:out value="${pagesCount}"/><br>
<form action="routes" method="get">
    <input type="hidden" name="page" value="${page -1}"><br>
    <input type="submit" value="Previous"
    <c:if test="${page==null||page==1}"> disabled </c:if> >
</form>

</body>
</html>
