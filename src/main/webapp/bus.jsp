<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<body>
<table border = "1" width = "100%">
  <tr>
    <th>ID</th>
    <th>Bus number</th>
  </tr>

  <c:forEach var = "bus" items = "${buses}">
    <tr>
      <td> <c:out value = "${bus.id}"/></td>
      <td> <c:out value = "${bus.busNumber}"/></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>