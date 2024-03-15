<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>Crear Cuenta</title>
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
	href="${pageContext.request.contextPath}/css/Registro.css">
</head>

<body>
	<nav class="navbar mb-5" id="navbar">
		<a class="navbar-brand active" href="#" aria-current="page"><span
			class="text-light m-5 fw-bold">Booking.com</span></a>
	</nav>
	<main>
		<div class="container mx-auto my-3">
			<div class="row">
				<div class="col-5 mx-auto">
					<c:choose>
						<c:when test="${not empty user.name}">
							<h3 class="mb-3">Editar Cuenta</h3>
						</c:when>
						<c:otherwise>
							<h3 class="mb-3">Crear Cuenta</h3>
						</c:otherwise>
					</c:choose>

					<h4 class="mb-2 text-danger">${messages.error}</h4>
					<form action="?" method="post">
					<input type="hidden" name="id" value="${user.id }">
						<div class="mb-3">
							<label class="form-label">Nombre</label> <input type="text"
								class="form-control" placeholder="Indica tu nombre" name="name"
								id="name" value="${user.name}" required> <label
								class="form-label">Apellidos</label> <input type="text"
								class="form-control" placeholder="Indica tus apellidos"
								name="surname" id="surname" value="${user.surname}" required>
							<label class="form-label">Email</label> <input type="email"
								class="form-control" placeholder="Indica tu dirección de email"
								name="email" id="email" value="${user.email}" required>
							<label class="form-label">Password</label> <input type="password"
								class="form-control" placeholder="Indica tu contraseña"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\w).{8,}"
								name="password" id="password" value="${user.password}"
								title="La contraseña debe contener al menos un dígito, una minúscula, una mayúscula, un carácter especial y tener al menos 8 caracteres."
								required> <label class="form-label">País/región</label>
							<select class="form-select" required>
								<option selected>Selecciona tu país/región</option>
								<option value="1">México</option>
								<option value="2">Estados Unidos</option>
								<option value="3">Canadá</option>
								<option value="4">España</option>
								<option value="5">Colombia</option>
								<option value="6">Argentina</option>
								<option value="7">Chile</option>
								<option value="8">Perú</option>
								<option value="9">Ecuador</option>
								<option value="10">Guatemala</option>
								<option value="11">Cuba</option>
								<option value="12">República Dominicana</option>
								<option value="13">Honduras</option>
								<option value="14">Bolivia</option>
								<option value="15">El Salvador</option>
							</select> <label class="form-label">Teléfono</label> <input type="tel"
								class="form-control" placeholder="Indica tu número de teléfono"
								required> <input type="submit"
								class="form-control btn btn-primary col-12"
								value=<c:choose>
      										<c:when test="${not empty user.id}"> "Confirmar cambios"</c:when>
    										<c:otherwise>
    											"Crear cuenta"
    										</c:otherwise>
					  					</c:choose> />
						</div>
					</form>
				</div>
			</div>
			<div class="row mt-2">
				<div class="col-5 mx-auto">
					<a href="LoginServlet.do" class="text-primary" target="_blank">¿Ya
						tienes cuenta?</a>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<div class="container mx-auto mt-4">
			<div class="row">
				<div class="col-12 text-center">
					<p class="text-secondary">© 2021 Booking.com. Todos los
						derechos reservados.</p>
				</div>
			</div>
		</div>
	</footer>
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
