<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bus Park</title>
</head>
<body>
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <form autocomplete="off" action="busPark" method="post">
        <h2> Bus ID: <input type="text" name="bus_id" value=""> <br></h2>
        <h2> User ID: <input type="text" name="user_id" value=""><br></h2>
        <h2> Route ID: <input type="text" name="route_id" value=""> <br></h2>
        <input type="submit" value="Execute"> <br>
    </form>
    <form autocomplete="off" action="busParkDelete" method="post">
        <h2> Bus ID: <input type="text" name="busPark_id" value=""> <br></h2>
        <input type="submit" value="Delete"><br>
    </form>
</c:if>


<table border="1" width="100%">
    <tr>
        <c:if test="${pageContext.request.isUserInRole('admin')}">
            <th>ID</th>
        </c:if>
        <th>Route ID</th>
        <th>Route Name</th>
        <th>Bus ID</th>
        <th>BusNumber</th>
        <th>User ID</th>
        <th>User Name</th>
        <th>Accepted</th>
        <c:if test="${pageContext.request.isUserInRole('driver')}">
            <th>Accept</th>
        </c:if>
    </tr>

    <c:forEach var="busPark" items="${busParks}">
        <tr>
            <c:if test="${pageContext.request.isUserInRole('admin')}">
                <td><c:out value="${busPark.id}"/></td>
            </c:if>
            <td><c:out value="${busPark.route.id}"/></td>
            <td><c:out value="${busPark.route.routeName}"/></td>
            <td><c:out value="${busPark.bus.id}"/></td>
            <td><c:out value="${busPark.bus.busNumber}"/></td>
            <td><c:out value="${busPark.user.id}"/></td>
            <td><c:out value="${busPark.user.user_name}"/></td>
            <td><c:out value="${busPark.accepted}"/></td>
            <c:if test="${pageContext.request.isUserInRole('driver')}">
                <td>
                    <form action="busParkAccept" method="post">
                        <input type="hidden" name="busPark_id" value="${busPark.id}">
                        <input type="submit" value="Accept"><br>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<c:if test="${pageContext.request.isUserInRole('admin')}">
<div style="display: inline-block">
    <div  style="font-size:10px;">
        <form action="busPark" method="get">
            <input type="hidden" name="page" value="${page == null ? 2 : page +1}"><br>
            <input type="submit" value="Next">
        </form>
    </div>
    <div  style="font-size:10px;">
        <c:out value="${pagesCount}"/><br>
    </div>
    <div  style="font-size:20px;">
        <form action="busPark" method="get">
            <input type="hidden" name="page" value="${page -1}"><br>
            <input type="submit" value="Previous"
            <c:if test="${page==null||page==1}"> disabled </c:if> >
        </form>
    </div>
</div>
</c:if>

</body>
</html>

