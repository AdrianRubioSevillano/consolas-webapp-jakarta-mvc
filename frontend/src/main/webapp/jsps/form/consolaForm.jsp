<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 27/5/26
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>

<c:choose>
    <c:when test="${action eq 'INSERT'}">
        <c:set var="urlActionForm" value="${mvc.uri('insertConsola')}"></c:set>
        <c:set var="cabecera" value="Añadir Consola"></c:set>
        <c:set var="method" value="POST"></c:set>
        <c:set var="boton" value="AÑADIR"></c:set>
        <c:set var="disabled" value=""></c:set>
    </c:when>
    <c:when test="${action eq 'UPDATE'}">
        <c:set var="urlActionForm" value="${mvc.uri('updateConsola', {'id':form.id})}"></c:set>
        <c:set var="cabecera" value="Modificar Consola"></c:set>
        <c:set var="method" value="PUT"></c:set>
        <c:set var="boton" value="MODIFICAR"></c:set>
        <c:set var="disabled" value=""></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="urlActionForm" value="${mvc.uri('deleteConsola', {'id':form.id})}"></c:set>
        <c:set var="cabecera" value="Eliminar Consola"></c:set>
        <c:set var="method" value="DELETE"></c:set>
        <c:set var="boton" value="ELIMINAR"></c:set>
        <c:set var="disabled" value="disabled"></c:set>
    </c:otherwise>
</c:choose>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${cabecera}</h1>
    <br/>

    <form action="${urlActionForm}" method="POST" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
        <input type="hidden" name="_method" value="${method}"/>
        <input type="hidden" name="id" value="${form.id}"/>

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="${form.nombre}" ${disabled}/>
        <ul>
            <c:forEach var="message" items="${errores.nombre}">
                <li>${message}</li>
            </c:forEach>
        </ul>
        <br/>

        <label for="fabricante">Fabricante:</label>
        <input type="text" name="fabricante" id="fabricante" value="${form.fabricante}" ${disabled}/>
        <ul>
            <c:forEach var="message" items="${errores.fabricante}">
                <li>${message}</li>
            </c:forEach>
        </ul>
        <br/>

        <label for="fechaLanzamiento">Fecha de Lanzamiento:</label>
        <input type="text" name="fechaLanzamiento" id="fechaLanzamiento" value="${form.fechaLanzamiento}" ${disabled}/>
        <ul>
            <c:forEach var="message" items="${errores.fechaLanzamiento}">
                <li>${message}</li>
            </c:forEach>
        </ul>
        <br/>

        <label for="foto">Foto:</label>
        <input type="text" name="foto" id="foto" value="${form.foto}" ${disabled}/>
        <ul>
            <c:forEach var="message" items="${errores.foto}">
                <li>${message}</li>
            </c:forEach>
        </ul>
        <br/>
        <input type="submit" value="${boton}"/>

    </form>

</body>
</html>
