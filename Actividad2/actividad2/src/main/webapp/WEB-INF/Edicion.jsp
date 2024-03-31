<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>${requestScope.CheckType}deAlojamiento</title>
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
	href="${pageContext.request.contextPath}/css/Edicion.css">

</head>

<body>

	<%@ include file="Cabecera.jsp" %>

	<div class="container">
		<div class="row">
			<div class="col-8 mx-auto">
				<h1 class="mb-3">${requestScope.CheckType} de Alojamiento</h1>
				<h4 class="mb-2 text-danger">${messages.error}</h4>
				<form method="post" action="?" class="pb-5">
					<input type="hidden" name="id" value="${property.id}"> <input
						type="hidden" name="idu" value="${property.idu}">
					<div class="mb-3">
						<label for="nombreAlojamiento" class="form-label">Nombre
							del Alojamiento</label> <input type="text" class="form-control" id="name"
							name="name" value="${property.name}" required>
					</div>
					<div class="mb-3">
						<label for="direccion" class="form-label">Dirección</label> <input
							type="text" class="form-control" id="address" name="address"
							value="${property.address}" required>
					</div>
					<div class="mb-3">
						<label for="telefono" class="form-label">Teléfono</label> <input
							type="tel" class="form-control" id="telephone" name="telephone"
							value="${property.telephone}" required>
					</div>
					<div class="mb-3">
						<label for="distanciaCentro" class="form-label">Distancia
							al centro</label> <input type="number" class="form-control"
							id="centerDistance" name="centerDistance" step="any" min="0"
							value="${property.centerDistance}" required>
					</div>
					<div class="mb-3">
						<label for="valoracionMedia" class="form-label">Valoración
							media</label> <input type="number" class="form-control"
							id="gradesAverage" name="gradesAverage" step="any" min="0"
							value="${property.gradesAverage}" required>
					</div>
					<div class="mb-3">
						<label for="ciudad" class="form-label">Ciudad</label> <input
							type="text" class="form-control" id="city" name="city"
							value="${property.city}" required>
					</div>
					<div class="mb-3">
						<label for="descripcion" class="form-label">Descripción</label> <input
							type="text" class="form-control" id="description"
							name="description" value="${property.description}" required>
					</div>

					<div class="mb-3">
						<p>Servicios o instalaciones ofrecidos</p>
						<c:forEach items="${services}" var="service">
							<div class="form-check">
								<c:choose>
									<c:when test="${checkedServices.contains(service.name)}">
										<input class="form-check-input" type="checkbox"
											value="${service.name}" id="${service.name}"
											name="listServices" checked>
										<label class="form-check label" for="${service.name}">${service.name}</label>
									</c:when>
									<c:otherwise>
										<input class="form-check-input" type="checkbox"
											value="${service.name}" id="${service.name}"
											name="listServices">
										<label class="form-check label" for="${service.name}">${service.name}</label>
									</c:otherwise>
								</c:choose>
							</div>

						</c:forEach>
					</div>

					<div class="mb-3">
						<p>Permite mascotas</p>
                        <c:choose>
                            <c:when test="${property.petFriendly == 1}">
						        <div class="form-check">
							        <input class="form-check-input" type="radio" name="petFriendly"
								        id="si" value="1" checked required> <label
								        class="form-check label" for="si">Sí</label>
						        </div>
						        <div class="form-check">
							        <input class="form-check-input" type="radio" name="petFriendly"
								        id="no" value="0" required> <label
								        class="form-check label" for="no">No</label>
						        </div>
                            </c:when>
                            <c:otherwise>
						        <div class="form-check">
							        <input class="form-check-input" type="radio" name="petFriendly"
								        id="si" value="1" required> <label
								        class="form-check label" for="si">Sí</label>
						        </div>
						        <div class="form-check">
							        <input class="form-check-input" type="radio" name="petFriendly"
								        id="no" value="0" checked required> <label
								        class="form-check label" for="no">No</label>
						        </div>
                            </c:otherwise>
                        </c:choose>
					</div>
					<!-- <button type="submit" class="btn btn-primary col-12">Guardar</button> -->
					<input type="submit" class="form-control btn btn-primary col-12"
						value="Guardar">
				</form>
			</div>
		</div>
	</div>



</body>

</html>
