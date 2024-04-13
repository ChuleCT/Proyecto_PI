<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Borrar habitación</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Borrar.css" />
    </head>

    <body>
        <%@ include file="Cabecera.jsp"%>
        
        <div class="centeredcontainer sizereduced">
            <ul>
                <li><span class="negrita">Nombre de la habitación:</span> ${accommodation.name}</li>
                <li><span class="negrita">Precio de la habitación:</span> ${accommodation.price}</li>
                <li><span class="negrita">Descripción de la habitación:</span> ${accommodation.description}</li>
                <li><span class="negrita">Número de habitaciones disponibles:</span> ${accommodation.numAccommodations}</li>
            </ul>
            <form method="POST" action="<c:url value='DeleteAccommodationServlet.do?id=${accommodation.id}'/>">
                <div class="divbutton">
                    <input class="btn btn-danger" type="submit" value="Borrar Habitación" />
                </div>		
            </form>
        </div>	
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>