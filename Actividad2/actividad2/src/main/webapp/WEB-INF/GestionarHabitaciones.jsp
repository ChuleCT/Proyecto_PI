<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>Reservas y viajes</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/DetalleAlojamiento.css">
</head>

<body>

	<%@ include file="Cabecera.jsp" %>

	<div class="container mb-5 pagina">
		<div class="row">
			<div class="col-12">
				<h3 class="fw-bold">Gestión de habitaciones</h3>
			</div>
			<a href="<c:url value='CreateAccommodationServlet.do?idp=${idp}'/>"
			 class="btn btn-primary col-2 fw-bold">Nueva habitación +</a>
		</div>
		<div class="row">
			<c:forEach items="${accommodationsList}" var="accommodation">
				<div class="col-6 mt-4">
					<fieldset class="shadow rounded">
						<div class="row pt-3 mb-4">
							<div class="col-8 ps-4">
								<p class="azul negrita">${accommodation.name}</p>
								<span class="negrita">${accommodation.description}</span>
								<p>
									Precio de la habitación:<span class="negrita">
										${accommodation.price}€</span>
								</p>
								<c:choose>
									<c:when test="${accommodation.numAccommodations > 1}">
										<span class="verde negrita">Número de habitaciones
											disponibles: ${accommodation.numAccommodations} </span>
									</c:when>
									<c:when test="${accommodation.numAccommodations == 1}">
										<span class=" negrita">Número de habitaciones
											disponibles: ${accommodation.numAccommodations} </span>
									</c:when>
									<c:otherwise>
										<span class="rojo negrita">No quedan habitaciones
											disponibles</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col text-end pe-5 mt-5">
								<a
									href="<c:url value='EditAccommodationServlet.do?id=${accommodation.id}'/>"
									class="btn btn-primary mb-2 negrita">Editar</a> <br> <br>
								<a
									href="<c:url value='DeleteAccommodationServlet.do?id=${accommodation.id}'/>"
									class="btn btn-danger">Eliminar</a>
							</div>
						</div>
					</fieldset>
				</div>
			</c:forEach>
		</div>
	</div>


</body>

</html>