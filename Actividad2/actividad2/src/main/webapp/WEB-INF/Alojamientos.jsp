<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Alojamientos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Alojamientos.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
</head>
<body>

	<%@ include file="Cabecera.jsp"%>

	<div class="container mt-5">

		<form action="AlojamientosServlet.do" method="post"">
			<!-- Barra de búsqueda, filtros y buscar:  -->
			<div class="row" id="busqueda">
				<div
					class="col-9 bg-primary offset-1 pt-2 bg-light border border-3 border-warning rounded">
					<input type="text" id="destino" name="destino"
						class="border border-0" placeholder="¿A dónde vas?" required>
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1 pt-1">
					<input type="submit"
						class="btn btn-primary border border-3 border-warning text-truncate"
						id="btn-buscar" value="Buscar">
				</div>
		</form>

	</div>
	</div>

	<!-- Parte sobre el fondo blanco de la página: -->
	<div class="container">
		<div class="row px-5" id="fondoBlanco">


			<div class="col-3">
				<div class="row">
					<div class="col-12">
						<div class="bordered-box p-2 tablaIzquierda">
							<p class="font-weight-bold mb-0">Filtrar por:</p>
						</div>
						<div class="bordered-box p-2 tablaIzquierdaSiguientes">
							<div class="btn-group-vertical" data-toggle="buttons">
								<label class="btn btn-outline-primary mb-0 w-100 text-start">
									<input type="radio" name="filtro" id="filtro_opcion1"
									value="Todos"
									<c:if test="${'Todos' eq opcionDisponibilidad}">checked</c:if>>
									Todos
								</label> <label class="btn btn-outline-primary mb-0 w-100 text-start">
									<input type="radio" name="filtro" id="filtro_opcion2"
									value="Con disponibilidad"
									<c:if test="${'Con disponibilidad' eq opcionDisponibilidad}">checked</c:if>>
									Con disponibilidad
								</label> <label class="btn btn-outline-primary mb-0 w-100 text-start">
									<input type="radio" name="filtro" id="filtro_opcion3"
									value="Sin disponibilidad"
									<c:if test="${'Sin disponibilidad' eq opcionDisponibilidad}">checked</c:if>>
									Sin disponibilidad
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>



			<!-- ################################################ -->
			<!-- Columna principal -->
			<!-- ################################################ -->
			<div class="col-8">


				<div class="row mb-5">
					<c:choose>
						<c:when test="${size eq 1}">
							<h4 class="negrita">${size} alojamiento encontrados</h4>
						</c:when>
						<c:otherwise>
							<h4 class="negrita">${size} alojamientos encontrados</h4>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn btn-primary mt-3"
						onclick="ordenar(true)">Ordenar por valoración</button>

				</div>

				<c:forEach var="alojamiento" items="${ListaAlojamientos}">
					<!-- Imágenes, nombre, precio, disponibilidad... de los alojamientos -->
					<c:set var="checked" value="no" />
					<div class="row bordered-box informacionAlojamiento mb-3">

						<div class="col-3">
							<img src="images/PalacioOquendo.png" class="my-2 imagenesPreview"
								id="imagenPalacio" alt="fachada Palacio de Oquendo">
							<!-- Corazón  -->
							<c:if test="${not empty user.name}">
								<form action="FavoritesPropertiesServlet.do" method="post">
								
									<input type="hidden" name="propertieId"
										value="${alojamiento.id}"> <input type="hidden"
										name="desdeAlojamiento" value="si">
									<input type="hidden" name="opcionParaFavoritos"
										value="${opcionDisponibilidad}">
										<input type="hidden" name="seOrdena"
										value="${seOrdena}">
										
									<c:if test="${fn:length(ListaFavoritos) > 0}">
										<c:forEach var="favorito" items="${ListaFavoritos}">
											<c:if test="${favorito.idp eq alojamiento.id}">
												<c:set var="checked" value="yes" />
											</c:if>
										</c:forEach>
									</c:if>
									<c:choose>
										<c:when test="${checked == 'yes'}">
											<label for="like-checkbox-${alojamiento.id}"
												class="heart-label"> <input type="checkbox"
												id="like-checkbox-${alojamiento.id}" checked
												class="heart-checkbox" name="favorito${alojamiento.id}"
												onchange="submitForm('like-checkbox-${alojamiento.id}')">
												<span class="heart-icon"> <i class="fas fa-heart"></i>
											</span>
											</label>
										</c:when>
										<c:otherwise>
											<label for="like-checkbox-${alojamiento.id}"
												class="heart-label"> <input type="checkbox"
												id="like-checkbox-${alojamiento.id}" class="heart-checkbox"
												name="favorito${alojamiento.id}"
												onchange="submitForm('like-checkbox-${alojamiento.id}')">
												<span class="heart-icon"> <i class="fas fa-heart"></i>
											</span>
											</label>
										</c:otherwise>
									</c:choose>
								</form>
							</c:if>

						</div>

						<div class="col-6 pt-1">
							<h4 class="negrita azul">${alojamiento.name}</h4>
							<div>
								<%-- Valoracion ESTRELLAS --%>
								<c:set var="estrellaEntera"
									value="${fn:split(alojamiento.gradesAverage, '.')[0]}" />
								<c:set var="estrellaMedia"
									value="${fn:split(alojamiento.gradesAverage, '.')[1]}" />
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
							<div class="row">
								<div class="col-auto">
									<p class="contenido-info negrita azul">
										${alojamiento.city}
									</p>
								</div>
								<div class="col-auto">
									<p class="contenido-info">
										<i class="fa-solid fa-location-dot"></i>
										a ${alojamiento.centerDistance}km del centro
									</p>
								</div>
							</div>
							<div class="row">
								<div class="bordered-box border-left">
									<div class="texto-con-linea negrita text-truncate">
										${alojamiento.description}</div>
									<div class="texto-con-linea">
										<c:choose>
											<c:when test="${alojamiento.petFriendly eq 1}">
                                                Permite mascotas
                                            </c:when>
											<c:otherwise>
                                                No permite mascotas
                                            </c:otherwise>
										</c:choose>
									</div>
									<div class="texto-con-linea negrita rojo">
										<c:choose>
											<c:when test="${alojamiento.available eq 1}">
                                                Disponible al menos una habitación
                                            </c:when>
											<c:otherwise>
                                                No disponible en el momento
                                            </c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>

						</div>

						<div class="col-3">
							<div class="row pt-1">
								<div class="col-8 text-end puntuacionTexto pt-2">
									<c:choose>
										<c:when test="${alojamiento.gradesAverage ge 4}">
											<b>Fabuloso</b><br>
										</c:when>
										<c:when test="${alojamiento.gradesAverage ge 2.5}">
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
										class="bg-primary text-light px-1 rounded fw-bold puntuacionMedia">${alojamiento.gradesAverage}</p>
								</div>


							</div>
							<div class="row">
								<div class="col pt-5 me-1 text-end  pb-4">
									<i class="fa-solid fa-phone ms-2"></i> <span>${alojamiento.telephone}</span>
									<a
										href="<c:url value='PropertyDetailsServlet.do?id=${alojamiento.id}'/>"
										class="btn btn-primary col-12"> Ver disponibilidad > </a>
								</div>
							</div>

						</div>
					</div>
				</c:forEach>

			</div>

		</div>

	</div>


	<script>
		function submitForm(checkboxId) {
			document.getElementById(checkboxId).form.submit();
		}
	</script>

	<script>
		function ordenar(seOrdena) {
			// Crea un formulario oculto
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "AlojamientosServlet.do");
			
			var inputOpcion = document.createElement("input");
			inputOpcion.setAttribute("type", "hidden");
			inputOpcion.setAttribute("name", "opcion");
			inputOpcion.setAttribute("value", "${opcionDisponibilidad}");
			form.appendChild(inputOpcion);

			// Crea un campo oculto para indicar si se debe eliminar la habitación
			var inputEliminar = document.createElement("input");
			inputEliminar.setAttribute("type", "hidden");
			inputEliminar.setAttribute("name", "seOrdena");
			inputEliminar.setAttribute("value", seOrdena);
			form.appendChild(inputEliminar);

			// Agrega el formulario al cuerpo del documento y envíalo
			document.body.appendChild(form);
			form.submit();
		}
	</script>

<script>
    function enviarFiltro(opcionSeleccionada) {
 
        var form = document.createElement("form");
        form.setAttribute("method", "get");
        form.setAttribute("action", "AlojamientosServlet.do");

        var inputOpcion = document.createElement("input");
        inputOpcion.setAttribute("type", "hidden"); // Aquí estaba el error
        inputOpcion.setAttribute("name", "opcion");
        inputOpcion.setAttribute("value", opcionSeleccionada);
        form.appendChild(inputOpcion);

        document.body.appendChild(form);
        form.submit();
    }

    // Obtener los radio buttons
    const radioButtons = document.querySelectorAll('input[name="filtro"]');
    
    radioButtons.forEach(radioButton => {
        radioButton.addEventListener('click', function () {
            // Obtener el valor del radio button seleccionado
            const selectedValue = this.value;
            // Llamar a la función para enviar el filtro seleccionado al servlet
            enviarFiltro(selectedValue);
        });
    });
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