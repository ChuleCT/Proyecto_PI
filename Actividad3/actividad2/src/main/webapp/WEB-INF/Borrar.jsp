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
       <%@ include file="Cabecera.jsp"%>

	<div class="centeredcontainer sizereduced">
		<c:choose>
			<c:when test="${requestScope.CheckType=='Borrar Usuario'}">
				<ul>
					<li><span class="negrita">Nombre usuario:</span> ${user.name}</li>
					<li><span class="negrita">Apellido usuario:</span> ${user.surname}</li>
					<li><span class="negrita">Email usuario:</span> ${user.email}</li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul>
					<li><span class="negrita">Nombre del alojamiento:</span>
						${property.name}</li>
					<li><span class="negrita">Dirección:</span>
						${property.address}</li>
					<li><span class="negrita">Teléfono:</span>
						${property.telephone}</li>
					<li><span class="negrita">Descipción:</span>
						${property.description}</li>
					<li><span class="negrita">Ciudad:</span> ${property.city}</li>
				</ul>
			</c:otherwise>
		</c:choose>
		<form method="POST" action="?">
			<div class="divbutton">
				<input class="btn btn-danger" type="submit"
					value="${requestScope.CheckType}" />
			</div>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>
</html>



