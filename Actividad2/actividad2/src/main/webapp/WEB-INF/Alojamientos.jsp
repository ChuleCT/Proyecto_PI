<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Alojamientos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Alojamientos.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <!-- glyphicon glyphicon-arrow-up -->
    </head>
    <body>

        <!-- navbar -->
        <div class="container-fluid color-navbar pt-3 pb-5">
            <div class="row" id="navbar">
                <div class="col-4 offset-2">
                    <a class="navbar-brand text-light fs-4 fw-bold" href="../Busqueda/index.html">Booking.com</a>
                </div>
                <div class="col-1">
                    <a href="../carrito/index.html" class="btn btn-light text-primary ms-4">
                        Carrito
                    </a>
                </div>
                <div class="col-1">
                    <a href="../Registro/index.html" class="btn btn-light text-primary">
                        Registrarse
                    </a>
                </div>
                <div class="col-2">
                    <a href="../inicioSesion/index.html" class="btn btn-light text-primary">
                        Iniciar Sesión
                    </a>
                </div>
            </div>
        </div>

        <div class="container">

            <form action="index.html">
                <!-- Barra de búsqueda, filtros y buscar:  -->
                <div class="row" id="busqueda">
                    <div
                            class="col-lg-3 col-md-2 col-sm-2 col-2 offset-1 pt-2 bg-light border border-3 border-warning rounded">
                        <input type="text" id="a-donde-vas" class="border border-0" placeholder="¿A dónde vas?" required>
                    </div>
                    <div class="col-lg-3 col-md-2 col-sm-2 col-2 pt-2 bg-light border border-3 border-warning rounded">
                        <div class="row">
                            <div class="col-5 me-4">
                                <input type="date">
                            </div>
                            <div class="col-5">
                                <input type="date">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-2 col-sm-2 col-2 pt-2 bg-light border border-3 border-warning rounded"
                         id="filtro-habitacion">
                        <div class="row">
                            <div class="col-4">
                                <input type="number" id="adultos" placeholder="Adultos">
                            </div>
                            <div class="col-4">
                                <input type="number" id="niños" placeholder="Niños">
                            </div>
                            <div class="col-4">
                                <input type="number" id="habitaciones" placeholder="Habitaciones">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-1 col-sm-1 pt-1">
                        <input type="submit" class="btn btn-primary border border-3 border-warning text-truncate"
                                             id="btn-buscar" value="Buscar">
                    </div>
            </form>
            <div class="row mt-2" id="caminoLinks">
                <div class="col-10">
                    <a href="../Busqueda/index.html">Inicio</a>  >
                    <a href="">España</a> >
                    <a href="">Extremadura</a> >
                    <a href="">Cáceres</a> >
                    Resultados de la búsqueda
                </div>
            </div>
                </div>
        </div>

        <!-- Parte sobre el fondo blanco de la página: -->
        <div class="container">
            <div class="row px-5" id="fondoBlanco">

                <!-- Columna de la izquierda principal -->
                <div class="col-3">
                    <img src="images/googleMaps.webp" id="imagenGoogleMaps" alt="google maps">
                    <button class="btn btn-primary" id="botonImagenGoogleMaps">Ver en el mapa</button>

                    <div class="row" >
                        <div class="col-12">
                            <div class="bordered-box tablaIzquierda">
                                <p class="contenido"><strong>Filtrar por:</strong></p>

                            </div>
                            <div class="bordered-box tablaIzquierdaSiguientes">
                                <p class="contenido-small"><b>Tu presupuesto (por noche)</b></p>
                                <p class="contenido-small">€ 30 - € 400+ </p>

                                <form>
                                    <div class="form-group contenido-small">
                                        <br>
                                        <input type="range" class="form-range" id="rangoNumerico" min="0" max="100" step="1" value="100">
                                    </div>
                                </form>
                            </div>

                            <div class="bordered-box tablaIzquierdaSiguientes">
                                <p class="contenido-small"><strong>Filtros populares:</strong></p>

                                <div id="checkboxFiltros">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla1">
                                        <label class="form-check-label" for="casilla1">
                                            Hoteles
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla2">
                                        <label class="form-check-label" for="casilla2">
                                            Desayuno incluido
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla3">
                                        <label class="form-check-label" for="casilla3">
                                            Fantástico: 9 o más
                                            <span class="contenido-small-small">Según los comentarios de los clientes</span>
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla4">
                                        <label class="form-check-label" for="casilla4">
                                            4 estrellas
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla5">
                                        <label class="form-check-label" for="casilla5">
                                            Hostales y pensiones
                                        </label>
                                    </div>

                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla6">
                                        <label class="form-check-label" for="casilla6">
                                            Cancelación gratis
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla7">
                                        <label class="form-check-label" for="casilla7">
                                            Parking
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="casilla8">
                                        <label class="form-check-label" for="casilla8">
                                            Villas
                                        </label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- ################################################ -->
                <!-- Columna de la derecha principal -->
                <!-- ################################################ -->
                <div class="col-8">


                    <div class="row">
                        <h4 class="negrita">Cáceres: 44 alojamientos encontrados </h4>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>
                            <!-- <div class="icon-container"> -->
                            <!--     <i class="fas fa-arrow-up flechas"></i> -->
                            <!--     <i class="fas fa-arrow-down flechas"></i> -->
                            <!-- </div> -->
                            Ordenar por: Nuestros destacados
                            </option>
                            <!-- <option value="1"></option> -->
                        </select>

                        <div class="alert alert-light alert-dismissible fade show mt-4" role="alert">
                            <strong>Un 79% de los alojamientos ya no están disponibles en nuestra web para esas fechas.</strong>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>

                        <div class="alert alert-secondary alert-dismissible fade show " role="alert">
                            <div class="contenido-small">
                                La comisión que pagan los alojamientos por las reservas y otros factores pueden influir en su puesto en el ranking.
                            </div>
                            <div class="contenido-small">
                                Descubre más sobre los parámetros del ranking y cómo seleccionarlos y modificarlos. <a href="">Más información</a>
                            </div>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </div>

                    <c:forEach var="alojamiento" items="${ListaAlojamientos}"> 
                            <p>Nombre alojamiento: ${alojamiento.name}</p>
                            <p>Direccion alojamiento: ${alojamiento.address}</p>
                            <p>Ciudad alojamiento: ${alojamiento.city}</p>
                            <p>Distancia del centro alojamiento: ${alojamiento.centerDistance}</p>
            	    </c:forEach>
                    <!-- Imágenes, nombre, precio, disponibilidad... de los alojamientos -->
                    <div class="row bordered-box informacionAlojamiento">

                        <div class="col-3">
                            <img src="images/PalacioOquendo.png" class="my-2 imagenesPreview" id="imagenPalacio" alt="fachada Palacio de Oquendo">
                            <!-- Corazón  -->
                            <label for="like-checkbox-1" class="heart-label">
                                <input type="checkbox" id="like-checkbox-1" class="heart-checkbox">
                                <span class="heart-icon"><i class="fas fa-heart"></i></span>
                            </label>
                        </div>

                        <div class="col-6 pt-1">
                            <h4 class="negrita azul">NH Collection Cáceres Palacio de Oquendo</h4>
                            <div>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                            </div>
                            <div class="row">
                                <div class="col-auto">
                                    <p class="contenido-info"><a href="">Cáceres</a></p>
                                </div>
                                <div class="col-auto">
                                    <p class="contenido-info"><a href="">Mostrar en el mapa</a></p>
                                </div>
                                <div class="col-auto">
                                    <p class="contenido-info">a 200m del centro</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="bordered-box border-left">
                                    <div class="texto-con-linea negrita">
                                        Habitación Doble Superior - 1 o 2 camas
                                    </div>
                                    <div class="texto-con-linea">
                                        Camas: 1 doble o 2 individuales
                                    </div>
                                    <div class="texto-con-linea negrita rojo">
                                        Solo queda 1 habitación a este precio en nuestra web
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="col-3">
                            <div class="row pt-1">
                                <div class="col-8 text-end puntuacionTexto">
                                    <b>Fabuloso</b><br>
                                    <div class="comentarios">
                                        4.592 comentarios
                                    </div>
                                </div>
                                <div class="col-3">
                                    <p class="bg-primary text-light px-1 rounded fw-bold puntuacionMedia">9,6</p>
                                </div>
                                <p class="text-end negrita azul">Ubicación 9,6</p>

                            </div>
                            <div class="row">
                                <div class="col pt-2 me-1 text-end  pb-4">
                                    <span class="comentarios">2 noches, 2 adultos </span> <br>
                                    <span class="fw-bold fs-4 precio">€ 285</span> <br>
                                    <span class="comentarios">Incluye impuestos y cargos</span>
                                    <a href="../detalleAlojamiento/index.html" class="btn btn-primary col-12">
                                        Ver disponibilidad >
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>





                    <!-- Imágenes, nombre, precio, disponibilidad... de los alojamientos -->

                    <div class="row bordered-box informacionAlojamiento my-2">

                        <div class="col-3">
                            <img src="images/PlazaDeItalia.png" class="my-2 imagenesPreview" id="plazaDeItalia" alt="foto hostal Plaza de Italia">
                            <!-- Corazón  -->
                            <label for="like-checkbox-2" class="heart-label">
                                <input type="checkbox" id="like-checkbox-2" class="heart-checkbox">
                                <span class="heart-icon"><i class="fas fa-heart"></i></span>
                            </label>
                        </div>

                        <div class="col-6 pt-1">
                            <h4 class="negrita azul">Hostal Plaza de Italia</h4>
                            <div class="rating">
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                            </div>
                            <div class="row">
                                <div class="col-auto">
                                    <p class="contenido-info"><a href="">Cáceres</a></p>
                                </div>
                                <div class="col-auto">
                                    <p class="contenido-info"><a href="">Mostrar en el mapa</a></p>
                                </div>
                                <div class="col-auto">
                                    <p class="contenido-info">a 400m del centro</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="bordered-box border-left">
                                    <div class="texto-con-linea negrita">
                                        Habitación doble económica
                                    </div>
                                    <div class="texto-con-linea">
                                        1 cama doble
                                    </div>
                                    <div class="texto-con-linea negrita rojo">
                                        Solo queda 1 habitación a este precio en nuestra web
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="col-3">
                            <div class="row pt-1">
                                <div class="col-8 text-end puntuacionTexto">
                                    <b>Muy bien</b><br>
                                    <div class="comentarios">
                                        808 comentarios
                                    </div>
                                </div>
                                <div class="col-3">
                                    <p class="bg-primary text-light px-1 rounded fw-bold puntuacionMedia">8,3</p>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col pt-2 me-1 text-end  pb-4">
                                    <span class="comentarios">2 noches, 2 adultos </span> <br>
                                    <span class="fw-bold fs-4 precio">€ 149</span> <br>
                                    <span class="comentarios">Incluye impuestos y cargos</span>
                                    <a href="../detalleAlojamiento/index.html" class="btn btn-primary col-12">
                                        Ver disponibilidad >
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

            </div>

        </div>





        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    </body>
</html>

