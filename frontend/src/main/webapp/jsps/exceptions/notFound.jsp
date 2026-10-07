<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 27/5/26
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>ERROR 404</h1>
  <br/>
  <label>No se ha encontrado la consola con ID: ${id}</label>
  <br/>
  <c:set var="urlGetAll" value="${mvc.uri('getAllConsolas')}"></c:set>
  <label><a href="${urlGetAll}">volver</a></label>

</body>
</html>
