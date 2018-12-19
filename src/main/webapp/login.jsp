<html>
<head>
    <title>Login</title>
</head>
<body>
<form autocomplete="off" method="POST" action="j_security_check">
    <table>
        <tr>
            <td colspan="2">Login to the BusDepo application:</td>
        </tr>
        <tr>
            <td>Login: </td>
            <td><input type="text" name= "j_username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Go" /></td>
        </tr>
    </table>
</form>
</body>

</html>