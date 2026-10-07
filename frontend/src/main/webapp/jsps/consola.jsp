<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 27/5/26
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>${consola.nombre}</h1>
  <br/>
  <table>

    <tr>
      <th>Nombre: </th>
      <td>${consola.nombre}</td>
    </tr>
    <tr>
      <th>Fabricante: </th>
      <td>${consola.fabricante}</td>
    </tr>
    <tr>
      <th>Fecha de Lanzamiento: </th>
      <td>${consola.fechaLanzamiento}</td>
    </tr>

    <tr>
      <th>Foto: </th>
      <td><img src="${consola.foto}" width="300"></td>
    </tr>



  </table>
  <c:set var="urlGetAll" value="${mvc.uri('getAllConsolas')}"></c:set>
  <label><a href="${urlGetAll}">volver</a></label>
</body>
</html>
