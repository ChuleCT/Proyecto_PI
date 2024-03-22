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
              href="${pageContext.request.contextPath}/css/Busqueda.css">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
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
                    <div class="col-1">
                        <a href="LogoutServlet.do" class="btn btn-light text-primary ms-4">
                            <i class="fa-solid fa-power-off"></i>
                        </a>
                    </div>
                    <div class="col-1">
                        <a href="../carrito/index.html" class="btn btn-light text-primary ms-4"> Carrito </a>
                    </div>
                    <div class="col-1">
                        <a href="<c:url value='EditUserServlet.do?id=${user.id}'/>" class="btn btn-light text-primary"> ${user.name}</a>
                    </div>
                    <div class="col-1">
                        <a href="ListaAlojamientosServlet.do" class="btn btn-light text-primary">Mis alojamientos</a>
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
        <main>
            <div class="container-fluid color-navbar pt-5">
                <div class="row mb-5" id="titulo">
                    <div class="col-8 offset-2">
                        <h1 class="mx-5 text-light fs-2 ">Encuentra tu próxima
                            estancia</h1>
                        <p class="mx-5 text-light pb-5">Busca ofertas en hoteles, casas
                        y mucho más...</p>
                    </div>
                </div>
            </div>
            <div class="container mx-auto">
                <form action="BusquedaServlet.do" method="post" >
                    <div class="row" id="busqueda">
                        <div class="col-9 bg-primary offset-1 pt-2 bg-light border border-3 border-warning rounded">
                            <input type="text" id="destino" name="destino" class="border border-0"
                                                                placeholder="¿A dónde vas?" required>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 pt-1">
                            <input type="submit" type="submit"
                                                 class="btn btn-primary border border-3 border-warning text-truncate"
                                                 id="btn-buscar" value="Buscar">
                        </div>
                        <div class="row mt-3">
                            <div class="col-2 offset-1">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox"> <label
                                                                    class="form-check-label"> Viajo por trabajo </label>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox"> <label
                                                                    class="form-check-label"> Busco vuelos </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="row" id="ofertas">
                    <div class="col-5 offset-1">
                        <h3>Ofertas</h3>
                        <p>Promociones, descuentos y ofertas especiales para ti</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-5">
                        <div class="card mb-3">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/familia.jpg" class="img-fluid rounded"
                                                                  alt="familia en puesta de sol">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Disfruta de tus vacaciones más
                                            largas</h5>
                                        <p class="card-text">Busca alojamientos que ofrezcan
                                        estancias largas, hay muchas con tarifa mensual reducida</p>
                                        <a href="../alojamientos/index.html" class="btn btn-primary">
                                            Elije tu estancia </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-5">
                        <div class="card mb-3">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/avion.jpeg" class="img-fluid rounded"
                                                                 alt="avión volando">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Vuela al destino de tus sueños</h5>
                                        <p class="card-text">Ínspirate, compara y reserva vuelos
                                        con más flexibilidad</p>
                                        <a href="../trending/index.html" class="btn btn-primary">
                                            Buscar vuelos </a>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col offset-1">
                        <h3>Busca por tipo de alojamiento</h3>
                    </div>
                </div>
                <div class="row" id="alojamientos">
                    <div class="col-3">
                        <img src="images/hotel.jpeg" class="rounded"
                                                     alt="habitación de hotel">
                        <p>Hoteles</p>
                    </div>
                    <div class="col-3">
                        <img src="images/apartamento.jpg" class="rounded"
                                                          alt="habitación de apartamento">
                        <p>Apartamentos</p>
                    </div>
                    <div class="col-3">
                        <img src="images/resort.jpeg" class="rounded"
                                                      alt="habitación de resort">
                        <p>Resorts</p>
                    </div>
                    <div class="col-3">
                        <img src="images/villa.jpg" class="rounded" alt="fachada de villa">
                        <p>Villas</p>
                    </div>
                </div>
            </div>
        </main>
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
