<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>Búsqueda</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Reserva.css">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
</head>

<body>
	<!-- navbar -->
	<div class="container-fluid color-navbar pt-2">
		<div class="row" id="navbar">
			<div class="col-4 offset-2">
				<a class="navbar-brand text-light fs-4 fw-bold"
					href="BusquedaServlet.do">Booking.com</a>
			</div>

			<c:choose>
				<c:when test="${not empty user.name}">

					<div class="col-1 offset-1 mt-1">
						<a href="../carrito/index.html" class="btn btn-light ms-4 fw-bold"><i
							class="fa-solid fa-cart-shopping"></i> Carrito </a>
					</div>
					<div class="col-1 mt-1">
						<div class="btn-group">
							<button type="button"
								class="btn btn-light dropdown-toggle fw-bold"
								data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${user.name}</button>
							<div class="dropdown-menu bg-secondary">
								<a class="dropdown-item fw-bold"
									href="<c:url value='EditUserServlet.do?id=${user.id}'/>"><i
									class="fa-solid fa-user"></i>Mi perfil</a> <a
									class="dropdown-item fw-bold"
									href="ListaAlojamientosServlet.do"><i
									class="fa-solid fa-house"></i> Mis alojamientos</a> <a
									class="dropdown-item fw-bold" href="BookingsServlet.do"><i
									class=""></i> Mis reservas</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item fw-bold" href="LogoutServlet.do"><i
									class="fa-solid fa-power-off"></i> Logout</a>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-1 offset-1">
						<a href="RegistroServlet.do" class="btn btn-light text-primary">
							Registrarse </a>
					</div>
					<div class="col-2">
						<a href="LoginServlet.do" class="btn btn-light text-primary">
							Iniciar Sesión </a>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

	<div class="container mb-5">
		<div class="row mt-3">
			<div class="col-3 offset-2">
				<h3 class="fw-bold">Reservas y viajes</h3>
			</div>
		</div>
		<c:forEach var="entry" items="${bookingsProperties}">
			<div class="row mt-4">
				<div class="col-8 offset-2">
					<h4 class="fw-bold">${entry.value.city}</h4>
					<fieldset class="shadow rounded">
						<div class="row py-3 ps-2">
							<div class="col-2 ps-4">
								<img class="rounded" src="images/DetalleAlojamiento3.png" alt="foto ${entry.value.name}">
							</div>
							<div class="col ms-5">
								<p>
									<span class="fw-bold">${entry.value.name}</span>
								</p>
								<p>${entry.value.address}</p>
								<p>${entry.value.description}</p>
								
							</div>
							<div class="col text-end pe-5">
								<p class="fw-bold fs-5 precio mt-5">Precio: ${entry.key.totalPrice}€</p>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- Bootstrap JavaScript Libraries -->
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
