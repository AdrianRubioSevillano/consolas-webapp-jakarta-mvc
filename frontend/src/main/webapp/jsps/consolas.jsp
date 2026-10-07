<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 27/5/26
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Consolas</h1>
  <br/>
  <c:set var="getFormInsert" value="${mvc.uri('getFormInsert')}"></c:set>
  <label><a href="${getFormInsert}">Añadir Consola</a></label>
  <table>
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Foto</th>
        <th>Acción</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="consolas" items="${consolas}">
        <c:set var="urlGetById" value="${mvc.uri('getConsolaById', {'id':consolas.id})}"></c:set>
        <c:set var="getFormUpdate" value="${mvc.uri('getFormUpdate', {'id':consolas.id})}"></c:set>
        <c:set var="getFormDelete" value="${mvc.uri('getFormDelete', {'id':consolas.id})}"></c:set>

        <tr>
          <td><a href="${urlGetById}">${consolas.nombre}</a></td>
          <td><img src="${consolas.foto}" width="150"></td>
          <td><a href="${getFormUpdate}">Modificar</a></td>
          <td><a href="${getFormDelete}">Eliminar</a></td>
        </tr>

      </c:forEach>

    </tbody>
  </table>

</body>
</html>
