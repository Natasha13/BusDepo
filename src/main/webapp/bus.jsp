<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<form autocomplete="off" action="buses" method="post">
    <h2> Bus name: <input type="text" name="bus_number" value=""><br></h2>
    <input type="submit" value="Execute"> <br>
</form>

<form autocomplete="off" action="busDelete" method="post">
    <h2> Bus ID: <input type="text" name="bus_id" value=""> <br></h2>
    <input type="submit" value="Delete"><br>
</form>

<table border="1" width="100%">
    <tr>
        <th>ID</th>
        <th>Bus number</th>
    </tr>

    <c:forEach var="bus" items="${buses}">
        <tr>
            <td><c:out value="${bus.id}"/></td>
            <td><c:out value="${bus.busNumber}"/></td>
        </tr>
    </c:forEach>
</table>

<form action="buses" method="get">
    <input type="hidden" name="page" value="${page == null ? 2 : page +1}"><br>
    <input type="submit" value="Next">
</form>

<c:out value="${pagesCount}"/><br>
<form action="buses" method="get">
    <input type="hidden" name="page" value="${page -1}"><br>
    <input type="submit" value="Previous"
    <c:if test="${page==null||page==1}"> disabled </c:if> >
</form>

</body>
</html>