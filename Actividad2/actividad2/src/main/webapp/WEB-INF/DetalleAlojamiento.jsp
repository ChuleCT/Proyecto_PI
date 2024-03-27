<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Detalle Alojamientos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/DetalleAlojamiento.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
</head>

<body>

	<!-- navbar -->
	<div class="container-fluid color-navbar pt-3">
		<div class="row" id="navbar">
			<div class="col-4 offset-2">
				<a class="navbar-brand text-light fs-4 fw-bold"
					href="BusquedaServlet.do">Booking.com</a>
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
	
	<c:set var="propertyPost" value="${property}" />

	<div class="container mb-5" id="pagina">
		<!-- Primera Row principal -->
		<div class="row" id="PrimeraColIzq">

			<!-- Columna principal de la izquierda -->
			<div class="col-3">
				<div class="row">

					<div class="col-12 bg-warning">
						<form action="index.html">
							<span class="fw-bold fs-5">Buscar</span> <br> <span
								class="texto-default mx-1">Destino:</span> <br>
							<div
								class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="mx-2 bi bi-search me-2"
									viewBox="0 0 16 16">
                                        <path
										d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                                    </svg>
								<input type="text" id="Destino" class="border border-0 mx-1"
									required>
							</div>
							<span class="texto-default mx-1">Fecha de entrada:</span> <br>
							<div
								class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
								<input type="date" class="border border-0 mx-1 pe-5">
							</div>
							<span class="texto-default mx-1">Fecha de salida:</span> <br>
							<div
								class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
								<input type="date" class="border border-0 mx-1 pe-5">
							</div>
							<span class="texto-default mx-1">Estancia de 2 noches:</span> <br>
							<div
								class="row bg-light border border-3 border-warning rounded mx-1">
								<div class="col-4">
									<input type="number" id="adultos" placeholder="Adultos" min="0"
										class="border border-0">
								</div>
								<div class="col-4">
									<input type="number" id="niños" placeholder="Niños" min="0"
										class="border border-0">
								</div>
								<div class="col-4">
									<input type="number" id="habitaciones"
										placeholder="Habitaciones" min="0" class="border border-0">
								</div>
							</div>
							<div class="form-check ms-2">
								<input class="form-check-input" type="checkbox"> Casas y
								apartamentos enteros
							</div>
							<div class="form-check ms-2">
								<input class="form-check-input" type="checkbox"> Viajo
								por trabajo

								<div class="col-lg-5 col-md-5 col-sm-5 pt-5">
									<input type="submit"
										class="btn btn-primary border border-3 border-warning text-truncate"
										id="btn-buscar" value="Buscar">
								</div>
							</div>

						</form>
					</div>
				</div>

				<div class="row mt-3">
					<div class="col-12">
						<img src="images/googleMaps.webp" class="img-fluid"
							alt="google maps">
						<button class="btn btn-primary" id="botonImagenGoogleMaps">Ver
							en el mapa</button>
					</div>
				</div>
			</div>

			<!-- Columna principal de la derecha -->
			<div class="col-9">
				<div class="row ms-1 ">
					<div class="col-10 ">
						<div class="rating">
							<span class="star"><i class="fas fa-star"></i></span> <span
								class="star"><i class="fas fa-star"></i></span> <span
								class="star"><i class="fas fa-star"></i></span> <span
								class="star"><i class="fas fa-star"></i></span>
						</div>
						<h4 class="negrita">${property.name}</h4>
						<div class="d-flex align-items-center">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-geo-alt" id="posGeo"
								viewBox="0 0 16 16">
                                    <path
									d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10" />
                                    <path
									d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6" />
                                </svg>
							<span>${property.address}</span> <i
								class="fa-solid fa-phone ms-2 me-2"></i> <span>${property.telephone}</span>
						</div>
						<a href="" class="negrita noSubrayado">Ubicación excelente -
							Ver mapa</a>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-geo-alt" id="posGeo"
							viewBox="0 0 16 16">
                                <path
								d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10" />
                                <path
								d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6" />
                            </svg>
						<span>A ${property.centerDistance} kms del centro</span>

					</div>

				</div>

				<div class="row ms-1">
					<div class="col-4">
						<img class="img-fluid mb-3" src="images/DetalleAlojamiento1.png"
							alt="habitación alojamiento Palacio de Oquendo"> <img
							class="img-fluid" src="images/DetalleAlojamiento2.png"
							alt="interior alojamiento Palacio de Oquendo">
					</div>
					<div class="col-8">
						<div class="position-relative">
							<img class="img-fluid" src="images/DetalleAlojamiento3.png"
								alt="fachada alojamiento Palacio de Oquendo">
							<div class="col-6 offset-6 textoEnImagen">
								<div class="row bg-light textoEnImagenRow">
									<div class="col-8 text-end puntuacionTexto">
										<b>Fabuloso</b><br>
										<div class="comentarios">4.592 comentarios</div>
									</div>
									<div class="col-3">
										<p
											class="bg-primary text-light px-1 mt-1 rounded fw-bold puntuacionMedia">${property.gradesAverage}</p>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="row ms-1 mt-2">
					<div class="col-3">
						<img class="img-fluid" src="images/DetalleAlojamiento8.png"
							alt="entrada alojamiento Palacio de Oquendo">
					</div>
					<div class="col-3">
						<img class="img-fluid" src="images/DetalleAlojamiento5.png"
							alt="cuarto de baño alojamiento Palacio de Oquendo">
					</div>
					<div class="col-3">
						<img class="img-fluid" src="images/DetalleAlojamiento6.png"
							alt="habitación alojamiento Palacio de Oquendo">
					</div>
					<div class="col-3">
						<img class="img-fluid" src="images/DetalleAlojamiento7.png"
							alt="fachada alojamiento Palacio de Oquendo">
					</div>
				</div>
			</div>
		</div>
		<!-- Termina la row principal de la izquierda -->

		<div class="container">
			<div class="row">
				<c:set var="index" value="${0}" />
				<c:forEach var="service" items="${services}">
					<c:if test="${index eq 5}">
						<div class="mb-1"></div>
						<c:set var="index" value="${0}" />
					</c:if>
					<div class="col border me-2 py-2">
						<i class="fa-regular fa-eye"></i> <span>${service}</span>
					</div>
					<c:set var="index" value="${index + 1}" />
				</c:forEach>
			</div>
		</div>

		<div class="row">
			<div class="col-8 mt-3">
				<p class="texto-default">${property.description}</p>

			</div>
			<!-- Termina col-8 (texto) -->


		</div>
		<!-- Termina la segunda row principal (texto y bg azul)-->


		<div class="row border my-5 py-3">
			<div class="col-10 mt-2">
				<p class="negrita fs-5">Inicia sesión y ahorra</p>
				<p>Podrías ahorrar un 20% o más en este alojamiento al iniciar
					sesión.</p>
				<div class="row">
					<div class="col-2">
						<!-- <a href="../inicioSesion/index.html"> -->
						<!--     <button class="btn btn-light border border-3 text-primary">Inicia sesión</button> -->
						<!-- </a> -->

						<a href="../inicioSesion/index.html"
							class="btn btn-light text-primary border border-3"> Inicia
							sesión </a>
					</div>
					<div class="col-2 mt-2">
						<a href="../Registro/index.html">Hazte una cuenta</a>
					</div>
				</div>
			</div>
			<div class="col-2">
				<img src="images/DetalleAlojamiento10.png" class="img-fluid"
					alt="ahorro por iniciar sesión">
			</div>
		</div>


		<h3 class="negrita">Disponibilidad</h3>

		<form action="index.html">
			<div class="row mb-5">
				<div class="col-3 border border-warning border-3 rounded">
					<input type="date" class=" form-control disponibilidad border-0"
						required>
				</div>
				<div class="col-5 border border-warning border-3 rounded">
					<div class="row">
						<div class="col-4">
							<input type="number"
								class=" form-control disponibilidad border-0"
								placeholder="Adultos -" min="0" required>
						</div>
						<div class="col-4">
							<input type="number"
								class=" form-control disponibilidad border-0"
								placeholder="Niños -" min="0" required>
						</div>
						<div class="col-4">
							<input type="number"
								class=" form-control disponibilidad border-0"
								placeholder="Habitaciones" min="0" required>
						</div>
					</div>
				</div>
				<div class="col-2">
					<input type="submit"
						class="btn btn-primary border-0 text-truncate py-2"
						id="ModificarBúsqueda" value="Modificar búsqueda">
				</div>
			</div>
		</form>

		<!-- Tabla disponibilidad -->
		<div class="container-fluid">
			<form action="PropertyDetailsServlet.do" method="post">
			<input type="hidden" name="id" value="${property.id}">
				<table class="table border">
					<thead class=" bg-primary">
						<tr>
							<th class="text-light negrita border py-3 col-4">Tipo de
								alojamiento</th>
							<th class="text-light negrita border py-3 col-3">Precio de
								la habitación</th>
							<th class="text-light negrita border py-3">Seleccionar
								habitaciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="accommodation" items="${accommodations}"
							varStatus="loop">
							<tr>
								<!-- Tipo de alojamiento -->
								<td>
									<p>
										<span class="negrita azul">${accommodation.name}</span>
									</p>
									<p class="texto-default">${accommodation.description}</p> <c:choose>
										<c:when test="${accommodation.numAccommodations eq 0}">
											<p class="rojo negrita texto-default">No quedan
												habitaciones en nuestra web</p>
										</c:when>
										<c:when test="${accommodation.numAccommodations > 1}">
											<p class="verde negrita texto-default">Quedan
												${accommodation.numAccommodations} habitaciones en nuestra
												web</p>
										</c:when>
										<c:otherwise>
											<p class="text-warning negrita texto-default">Solo queda
												1 habitación en nuestra web!</p>
										</c:otherwise>
									</c:choose>
									<div class="container-fluid mt-3 border-2">
										<div class="row d-inline-flex align-items-center">
											<c:forEach var="service" items="${services}">
												<div class="col-auto">
													<i class="fa-solid fa-check verde"></i> <span
														class="texto-default">${service}</span>
												</div>
											</c:forEach>
										</div>
									</div>
								</td>
								<!-- Precio para 2 personas -->
								<td class="item-align-center"><span class="negrita">€
										${accommodation.price}</span> <span class="texto-default">Incluye
										impuestos y cargos</span></td>
								<!-- Seleccionar habitaciones -->
								<td><input type="number" name="cantidad${loop.index}"
									min="0" max="${accommodation.numAccommodations}" step="1"
									value=0 class="mt-2"></td>
								<!-- Botón reservaré -->
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3" class="text-center"><input type="submit"
								class="btn btn-primary border-0 text-truncate" id="Reservar"
								value="Reservar"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<!-- finaliza la tabla de disponibilidad -->
	</div>
	<!-- Termina el container principal -->



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
