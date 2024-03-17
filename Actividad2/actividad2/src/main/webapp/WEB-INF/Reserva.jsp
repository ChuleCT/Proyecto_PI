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
	href="${pageContext.request.contextPath}/css/Reserva.css">
</head>

<body>

	<!-- navbar -->
	<div class="container-fluid color-navbar pt-3 mb-4">
		<div class="row" id="navbar">
			<div class="col-4 offset-2">
				<a class="navbar-brand text-light fs-4 fw-bold"
					href="../Busqueda/index.html">Booking.com</a>
			</div>
			<div class="col-1">
				<a href="../carrito/index.html"
					class="btn btn-light text-primary ms-4"> Carrito </a>
			</div>
			<div class="col-1">
				<a href="../Registro/index.html" class="btn btn-light text-primary">
					Registrarse </a>
			</div>
			<div class="col-2">
				<a href="../inicioSesion/index.html"
					class="btn btn-light text-primary"> Iniciar Sesión </a>
			</div>
		</div>
	</div>

	<div class="container mb-5">
		<div class="row">
			<div class="col-3 offset-2">
				<h3 class="fw-bold">${requestScope.CheckType}</h3>
			</div>
		</div>
		<c:forEach items="${ListaAlojamientos}" var="alojamiento">
		<div class="row mt-4">
			<div class="col-8 offset-2">
				<h4 class="fw-bold">${alojamiento.city}</h4>
				<fieldset class="shadow rounded">
					<div class="row py-3 ps-2">
						<div class="col-2 ps-4">
							<img class="rounded mt-2" src="images/ilunion.jpg"
								alt="foto Ilunion Suites Madrid">
						</div>
						<div class="col">
							<p>
								<span class="fw-bold">${alojamiento.name}</span>
							</p>
								<span>${alojamiento.address}</span>
								<p>${alojamiento.telephone}</p>
								<p class="contenido-info">a ${alojamiento.centerDistance}km
										del centro</p>
							</div>
						<div class="col text-end pe-5">
							<a href="<c:url value='EditPropertyServlet.do?id=${alojamiento.id}'/>" class="btn btn-primary mt-4" >Editar</a>
							<br>
							<a href="<c:url value='DeletePropertyServlet.do?id=${alojamiento.id}'/>" class="btn btn-danger mt-3" >Eliminar</a>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
		</c:forEach>
	</div>

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
