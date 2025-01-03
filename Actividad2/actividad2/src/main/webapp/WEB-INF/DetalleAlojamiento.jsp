<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

	<%@ include file="Cabecera.jsp"%>

	<c:set var="propertyPost" value="${property}" />

	<div class="container mb-5 pagina">
		<!-- Primera Row principal -->
		<div class="row" id="PrimeraColIzq">


			<!-- Columna principal de la derecha -->
			<div class="col-12">
				<div class="row ms-1 ">
					<div class="col-10 ">
						<div>
							<%-- Valoracion ESTRELLAS --%>
							<c:set var="estrellaEntera"
								value="${fn:split(property.gradesAverage, '.')[0]}" />
							<c:set var="estrellaMedia"
								value="${fn:split(property.gradesAverage, '.')[1]}" />
							<%-- Itera sobre la parte entera --%>
							<c:forEach var="i" begin="1" end="${estrellaEntera}">
								<i class="fas fa-star"
									style="color: #b8b814; position: relative; top: -0.5em;"></i>
							</c:forEach>
							<%-- Verifica si hay una parte decimal --%>
							<c:if test="${not empty estrellaMedia}">

								<%-- Si la parte decimal es .5, añade una estrella media --%>
								<c:if test="${estrellaMedia ge '5'}">
									<i class="fas fa-star-half-alt"
										style="color: #b8b814; position: relative; top: -0.5em;"></i>
								</c:if>


							</c:if>
						</div>
						<h4 class="negrita">${property.name}</h4>
						<div class="d-flex align-items-center">
							<i class="fa-solid fa-location-dot"></i> <span>
								${property.address}</span> <i class="fa-solid fa-phone ms-2 me-2"></i>
							<span>${property.telephone}</span>
						</div>

						<i class="fa-solid fa-location-dot"></i> <span>A
							${property.centerDistance} kms del centro</span>

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
							<div class="col-6 offset-8 textoEnImagen mt-3">
								<div class="row bg-light textoEnImagenRow">
									<div class="col-8 text-end puntuacionTexto pt-2">
										<c:choose>
											<c:when test="${property.gradesAverage ge 4}">
												<b>Fabuloso</b>
												<br>
											</c:when>
											<c:when test="${property.gradesAverage ge 2.5}">
												<b>Aceptable</b>
												<br>
											</c:when>
											<c:otherwise>
												<b>Regular</b>
												<br>
											</c:otherwise>
										</c:choose>
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

		<div class="container mt-3">
			<div class="row">
				<c:set var="index" value="${0}" />
				<c:forEach var="service" items="${services}">
					<div class="col-2">
						<c:if test="${index eq 5}">
							<div class="mb-1"></div>
							<c:set var="index" value="${0}" />
						</c:if>
						<div class="col border me-2 py-2 ps-2 mb-2">
							<i class="${service.icon}"></i> <span>${service.name}</span>
						</div>
						<c:set var="index" value="${index + 1}" />
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="row">
			<div class="col-12 mt-3">
				<p class="texto-default">${property.description}</p>

			</div>
			<!-- Termina col-8 (texto) -->


		</div>
		<!-- Termina la segunda row principal (texto y bg azul)-->


			<c:if test="${empty user.name}">
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
			</c:if>




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
								<td class="col-5">
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
														class="texto-default">${service.name}</span>
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

		<%-- Verificar si el usuario actual es propietario de la propiedad --%>
		<c:if test="${not empty user.name && user.id ne property.idu}">
			<div class="row justify-content-center">
				<div class="col">
					<div class="card">
						<div class="card-header">Deja tu opinión</div>
						<div class="card-body">
							<form action="ReviewServlet.do" method="post">
								<input type="hidden" name="idPropertyReviewed"
									value="${property.id}">
								<div class="form-group row">
									<label for="grade"
										class="col-md-3 col-form-label text-md-right">Calificación</label>
									<div class="col-9">
										<select id="grade" name="grade" class="form-control">
											<option value="1"
												${userReview != null && userReview.grade == 1 ? "selected" : ""}>1</option>
											<option value="2"
												${userReview != null && userReview.grade == 2 ? "selected" : ""}>2</option>
											<option value="3"
												${userReview != null && userReview.grade == 3 ? "selected" : ""}>3</option>
											<option value="4"
												${userReview != null && userReview.grade == 4 ? "selected" : ""}>4</option>
											<option value="5"
												${userReview != null && userReview.grade == 5 ? "selected" : ""}>5</option>
										</select>
									</div>
								</div>
								<div class="form-group row mt-2">
									<label for="comment"
										class="col-md-3 col-form-label text-md-right">Comentario</label>
									<div class="col-md-9">
										<textarea id="comment" name="comment" class="form-control"
											placeholder="Escribe tu comentario aquí" rows="4">${userReview != null ? userReview.review : ""}</textarea>
									</div>
								</div>
								<div class="form-group row mt-2">
									<div class="col-md-3 offset-md-3">
										<button id="submitButton" type="submit"
											class="btn btn-primary">${userReview != null ? "Modificar review" : "Enviar"}</button>

										<button type="button" class="btn btn-link ms-2"
											onclick="eliminar(true)">
											<i class="fa fa-trash text-danger"></i>
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

		</c:if>



		<!-- Mostrar opiniones existentes -->
		<div class="row mt-5">
			<div class="col-12">
				<h3 class="negrita">Opiniones de otros usuarios</h3>
				<c:forEach var="review" items="${reviews}" varStatus="status">
					<div class="card mb-3">
						<div class="card-header">Usuario: ${users[status.index]}</div>
						<div class="card-body">
							<h5 class="card-title">Calificación: ${review.grade}</h5>
							<p class="card-text">Comentario: ${review.review}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>



	</div>

	<script>
		function eliminar(seBorra) {
			// Crea un formulario oculto
			var form = document.createElement("form");
			form.setAttribute("method", "post");
			form.setAttribute("action", "ReviewServlet.do");

			var inputId = document.createElement("input");
			inputId.setAttribute("type", "hidden");
			inputId.setAttribute("name", "idPropertyReviewed");
			inputId.setAttribute("value", "${property.id}");
			form.appendChild(inputId);

			// Crea un campo oculto para indicar si se debe eliminar la habitación
			var inputEliminar = document.createElement("input");
			inputEliminar.setAttribute("type", "hidden");
			inputEliminar.setAttribute("name", "seBorra");
			inputEliminar.setAttribute("value", seBorra);
			form.appendChild(inputEliminar);

			// Agrega el formulario al cuerpo del documento y envíalo
			document.body.appendChild(form);
			form.submit();
		}
	</script>



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
