
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<html>
<head>
    <title>User</title>
</head>
<body>
<form autocomplete="off"  action="users" method="post" >
    <h2> User name: <input type="text" name="user_name" value=" "><br></h2>
    <h2> Login: <input type="text" name="login" value=" "> <br></h2>
    <h2> Password: <input type="text" name="password" value=" "> <br></h2>
    <h2> Speciality: <input type="text" name="user_spesiality" value=" "> <br></h2>
    <input type="submit" value="Execute"> <br>
</form>

<form autocomplete="off" action="usersDelete" method="post">
    <h2> Bus ID: <input type="text" name="user_id" value=" "> <br></h2>
    <input type="submit" value="Delete"><br>
</form></td>

<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Login</th>
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
