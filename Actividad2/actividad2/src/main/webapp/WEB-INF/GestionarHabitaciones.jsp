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

        <!-- navbar -->
        <div class="container-fluid color-navbar pt-3">
            <div class="row" id="navbar">
                <div class="col-4 offset-2">
                    <a class="navbar-brand text-light fs-4 fw-bold"
                       href="BusquedaServlet.do">Booking.com</a>
                </div>

                <c:choose>
                <c:when test="${not empty user.name}">

                <div class="col-1 offset-1">
                    <a href="../carrito/index.html" class="btn btn-light ms-4 fw-bold"><i class="fa-solid fa-cart-shopping"></i> Carrito </a>
                </div>
                <div class="col-1">
                    <div class="btn-group">
                        <button type="button" class="btn btn-light dropdown-toggle fw-bold" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.name}
                        </button>
                        <div class="dropdown-menu bg-secondary">
                            <a class="dropdown-item fw-bold" href="<c:url value='EditUserServlet.do?id=${user.id}'/>"><i class="fa-solid fa-user"></i>Mi perfil</a>
                            <a class="dropdown-item fw-bold" href="ListaAlojamientosServlet.do"><i class="fa-solid fa-house"></i> Mis alojamientos</a>
                            <a class="dropdown-item fw-bold" href=""><i class="fa-solid fa-list"></i> Mis reservas</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item fw-bold" href="LogoutServlet.do"><i class="fa-solid fa-power-off"></i> Logout</a>
                        </div>
                    </div>
                </div>
                                </c:when>
                                <c:otherwise>
                                <div class="col-1 offset-1">
                                    <a href="RegistroServlet.do"
                                       class="btn btn-light text-primary"> Registrarse </a>
                                </div>
                                <div class="col-2">
                                    <a href="LoginServlet.do"
                                       class="btn btn-light text-primary"> Iniciar Sesión </a>
                                </div>
                                </c:otherwise>
                </c:choose>

            </div>
        </div>

        <div class="container mb-5 pagina">
            <div class="row">
                <div class="col-12">
                    <h3 class="fw-bold">Gestión de habitaciones</h3>
                </div>
                <a href="" class="btn btn-primary col-2 fw-bold">Nueva habitación +</a>
            </div>
            <div class="row mt-4">
                <c:forEach items="${accommodationsList}" var="accommodation">
                    <div class="col-6">
                        <fieldset class="shadow rounded">
                            <div class="row pt-3 mb-4">
                                <div class="col-8 ps-4">
                                    <p class="azul negrita">
                                        ${accommodation.name}
                                    </p>
                                    <span class="negrita">${accommodation.description}</span>
                                    <p>Precio de la habitación:<span class="negrita"> ${accommodation.price}€</span></p>
                                    <c:choose>
                                    <c:when test="${accommodation.numAccommodations > 1}">
                                        <span class="verde negrita">Número de habitaciones disponibles: ${accommodation.numAccommodations} </span>
                                    </c:when>
                                    <c:when test="${accommodation.numAccommodations == 1}">
                                        <span class=" negrita">Número de habitaciones disponibles: ${accommodation.numAccommodations} </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="rojo negrita">No quedan habitaciones disponibles</span>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col text-end pe-5 mt-5">
                                    <a href="<c:url value='EditAccommodationServlet.do?id=${accommodation.id}'/>" class="btn btn-primary mb-2 negrita" >Editar</a>
                                    <br>
                                    <br>
                                    <a href="<c:url value='DeleteAccommodationServlet.do?id=${accommodation.id}'/>" class="btn btn-danger" >Eliminar</a>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </c:forEach>
            </div>
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
