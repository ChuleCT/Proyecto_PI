<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>Carrito de la compra</title>
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

<!-- Font Awesome v5.15.4 -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Carrito.css">

<style>
.fondo {
	background-color: #cfeeff;
}
</style>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid color-navbar pt-3">
            <div class="row" id="navbar">
                <div class="col-4 offset-2">
                    <a class="navbar-brand text-light fs-4 fw-bold"
                       href="BusquedaServlet.do">Booking.com</a>
                </div>
                        <div class="col-1 offset-1">
                            <div class="btn-group">
                                <button type="button" class="btn btn-light dropdown-toggle fw-bold" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${user.name}
                                </button>
                                <div class="dropdown-menu bg-secondary">
                                    <a class="dropdown-item fw-bold" href="<c:url value='EditUserServlet.do?id=${user.id}'/>"><i class="fa-solid fa-user"></i>Mi perfil</a>
                                    <a class="dropdown-item fw-bold" href="ListaAlojamientosServlet.do"><i class="fa-solid fa-house"></i> Mis alojamientos</a>
                                    <a class="dropdown-item fw-bold" href="BookingsServlet.do"><i class=""></i> Mis reservas</a>
                                    <a class="dropdown-item fw-bold" href="FavoritesPropertiesServlet.do"><i class="fa-solid fa-heart"></i> Mis favoritos</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item fw-bold" href="LogoutServlet.do"><i class="fa-solid fa-power-off"></i> Logout</a>
                                </div>
                            </div>
                        </div>

            </div>
        </div>

	<c:choose>
		<c:when test="${not empty property.name}">

			<div class="container mt-5">
				<div class="row">
					<div class="col-4 mx-auto">
						<div class="row">
							<div class="col border rounded">
								<h5 class="fw-bold mt-3">${property.name}</h5>
								<p>${property.address}</p>
								<p>
									<span
										class="bg-primary text-light px-1 rounded fw-bold puntuacionMedia">${property.gradesAverage}</span>
									Fabuloso
								</p>

								<c:forEach var="service" items="${services}">
									<p class="me-2">
										<i class="${service.icon} me-2" aria-hidden="true"></i>${service.name}</p>
								</c:forEach>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col border rounded">
								<h5 class="fw-bold mb-3 mt-3">Los datos de tu reserva</h5>
								<p>Has seleccionado:</p>
								<div class="list-group list-group-flush">
									<c:forEach var="entry" items="${selectedAccommodations}">
										<div
											class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
											<div class="row w-100">
												<div class="col-9 d-flex align-items-center text-truncate">
													<span>${entry.key.name}</span>
												</div>
												<div
													class="col-3 d-flex justify-content-end align-items-center">
													<span class="badge bg-primary rounded-pill">${entry.value}
														Habitaciones</span>
													<button type="button" class="btn btn-link ms-2"
														onclick="eliminar('${entry.key.id}', true)">
														<i class="fa fa-trash text-danger"></i>
													</button>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="row mt-3 mb-5">
							<div class="col border rounded">
								<h5 class="fw-bold mb-3 mt-3">Desglose del precio</h5>
								<div class="row fondo mb-3">
									<div class="col">
										<h4 class="fw-bold mt-3">Total</h4>
									</div>
									<div class="col">
										<h4 class="fw-bold text-end mt-3">€ ${totalPrice}</h4>
										<p>Incluye impuestos y cargos</p>
									</div>
								</div>
								<h6 class="fw-bold">Información sobre el precio</h6>
								<p>
									<i class="fa fa-credit-card" aria-hidden="true"></i> Incluye €
									${totalPrice * 0.10} de impuestos y cargos
								</p>
								<p>10% de IVA // € ${totalPrice * 0.10}</p>
								<form action="ShoppingCartServlet.do" method="post">
									<input type="hidden" name="totalPrice" value="${totalPrice}">
									<button type="submit" class="btn btn-primary w-100 mb-2">Reservar</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</c:when>
		<c:otherwise>
			<div class="container mt-5">
				<div class="row">
					<div class="col-4 mx-auto">
						<div class="row">
							<div class="col border
								rounded">
								<h5 class="fw-bold mt-3">No hay datos de reserva</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>



	<script>
		function eliminar(id, eliminarHabitacion) {
			// Crea un formulario oculto
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", "ShoppingCartServlet.do");

			// Crea un campo oculto para enviar el ID del alojamiento
			var inputId = document.createElement("input");
			inputId.setAttribute("type", "hidden");
			inputId.setAttribute("name", "accommodationId");
			inputId.setAttribute("value", id);
			form.appendChild(inputId);

			// Crea un campo oculto para indicar si se debe eliminar la habitación
			var inputEliminar = document.createElement("input");
			inputEliminar.setAttribute("type", "hidden");
			inputEliminar.setAttribute("name", "eliminarHabitacion");
			inputEliminar.setAttribute("value", eliminarHabitacion);
			form.appendChild(inputEliminar);

			// Agrega el formulario al cuerpo del documento y envíalo
			document.body.appendChild(form);
			form.submit();
		}
	</script>

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
