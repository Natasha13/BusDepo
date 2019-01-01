<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User</title>
</head>
<body>
<form autocomplete="off" action="users" method="post">
    <h2> Full name: <input type="text" name="user_name" value=""><br></h2>
    <h2> Login: <input type="text" name="login" value=""> <br></h2>
    <h2> Password: <input type="text" name="password" value=""> <br></h2>
    <h2> Role: <input type="text" name="user_role" value=""> <br></h2>
    <input type="submit" value="Execute"> <br>
</form>

<form autocomplete="off" action="usersDelete" method="post">
    <h2> ID: <input type="text" name="user_id" value=""> <br></h2>
    <input type="submit" value="Delete"><br>
</form>
</td>

<table border="1" width="100%">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Login</th>
        <th>Role</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.user_name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.user_role}"/></td>
        </tr>
    </c:forEach>
</table>

<div class="w3-show-inline-block">
    <div class="w3-bar">
        <form  action="users" method="get" align="left">
            <input type="hidden" name="page" value="${page == null ? 2 : page +1}"><br>
            <input type="submit" value="Next">
        </form>
        <%--<c:out value="${pagesCount}"/><br>--%>
        <form action="users" method="get" align="right">
            <input type="hidden" name="page" value="${page -1}"><br>
            <input type="submit" value="Previous"
            <c:if test="${page==null||page==1}"> disabled </c:if> >
        </form>
    </div>
</div>

</body>

</html>
