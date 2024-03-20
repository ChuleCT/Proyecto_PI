<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${requestScope.CheckType} Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Borrar.css" />

    </head>

    <body>
        <header class="bg-primary">
            <h1>${requestScope.CheckType} </h1>
        </header>
        <div class="centeredcontainer sizereduced">
            <c:choose>
                <c:when test="${requestScope.CheckType=='Borrar Usuario'}">
                    <ul>
                        <li>Nombre usuario: ${user.name}</li>
                        <li>Apellido usuario: ${user.surname}</li>
                        <li>Email usuario: ${user.email}</li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li>Nombre del alojamiento: ${property.name}</li>
                        <li>Dirección: ${property.address}</li>
                        <li>Teléfono: ${property.telephone}</li>
                        <li>Descipción: ${property.description}</li>
                        <li>Ciudad: ${property.city}</li>
                    </ul>
                </c:otherwise>	
            </c:choose>
            <form method="POST" action="?">
                <div class="divbutton">
                    <input class="btn btn-primary" type="submit" value="${requestScope.CheckType}"/>
                </div>		
            </form>
        </div>	
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>



