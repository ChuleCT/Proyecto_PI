<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Detalle Alojamientos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DetalleAlojamiento.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    </head>

    <body>

        <!-- navbar -->
        <div class="container-fluid color-navbar pt-3">
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

        <div class="container mb-5" id="pagina">
            <!-- Primera Row principal --> 
            <div class="row" id="PrimeraColIzq">

                <!-- Columna principal de la izquierda --> 
                <div class="col-3">
                    <div class="row">

                        <div class="col-12 bg-warning">
                            <form action="index.html">
                                <span class="fw-bold fs-5">Buscar</span> <br>
                                <span class="texto-default mx-1">Destino:</span> <br>
                                <div class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="mx-2 bi bi-search me-2" viewBox="0 0 16 16">
                                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                    </svg>
                                    <input type="text" id="Destino" class="border border-0 mx-1" required>
                                </div>
                                <span class="texto-default mx-1">Fecha de entrada:</span> <br>
                                <div class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
                                    <input type="date" class="border border-0 mx-1 pe-5">
                                </div>
                                <span class="texto-default mx-1">Fecha de salida:</span> <br>
                                <div class="pt-2 bg-light border border-3 border-warning rounded d-flex align-items-center">
                                    <input type="date" class="border border-0 mx-1 pe-5">
                                </div>
                                <span class="texto-default mx-1">Estancia de 2 noches:</span> <br>
                                <div class="row bg-light border border-3 border-warning rounded mx-1">
                                    <div class="col-4">
                                        <input type="number" id="adultos" placeholder="Adultos" min="0" class="border border-0" >
                                    </div>
                                        <div class="col-4">
                                            <input type="number" id="niños" placeholder="Niños" min="0" class="border border-0">
                                        </div>
                                        <div class="col-4">
                                            <input type="number" id="habitaciones" placeholder="Habitaciones" min="0" class="border border-0" >
                                        </div>
                                </div>
                                <div class="form-check ms-2">
                                    <input class="form-check-input" type="checkbox">
                                    Casas y apartamentos enteros
                                </div>
                                <div class="form-check ms-2">
                                    <input class="form-check-input" type="checkbox">
                                    Viajo por trabajo

                                    <div class="col-lg-5 col-md-5 col-sm-5 pt-5">
                                        <input type="submit" class="btn btn-primary border border-3 border-warning text-truncate"
                                                             id="btn-buscar" value="Buscar">
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-12">
                            <img src="../img/googleMaps.webp" class="img-fluid" alt="google maps">
                            <button class="btn btn-primary" id="botonImagenGoogleMaps">Ver en el mapa</button>
                        </div>
                    </div>
                </div> 

                <!-- Columna principal de la derecha -->
                <div class="col-9">
                    <div class="row ms-1 ">
                        <div class="col-10 ">
                            <div class="rating">
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                                <span class="star"><i class="fas fa-star"></i></span>
                            </div>
                            <h4 class="negrita">NH Collection Cáceres Palacio de Oquendo</h4>
                            <div class="d-flex align-items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" id="posGeo" viewBox="0 0 16 16">
                                    <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                                    <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                </svg>
                                <span>Plaza de San Juan, 11, 10003 Cáceres, España -</span>
                                <i class="fa-solid fa-phone ms-2"></i>
                                <span>+XX XXX XX XX XX</span>
                            </div>
                            <a href="" class="negrita noSubrayado">Ubicación excelente - Ver mapa</a>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" id="posGeo" viewBox="0 0 16 16">
                                <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                                <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                            </svg>
                            <span>A 100 m del centro histórico de Cáceres</span>

                        </div>

                        <div class="col-2">
                            <a href="../carrito/index.html" class="btn btn-primary border-0 text-truncate">
                                Reserva ahora
                            </a>
                            <a href="../edicion/index.html" class="btn btn-danger border-0 text-truncate mt-2 ms-4">
                                Editar
                            </a>
                        </div>
                    </div>

                    <div class="row ms-1">
                        <div class="col-4">
                            <img class="img-fluid mb-3" src="../img/DetalleAlojamiento1.png" alt="habitación alojamiento Palacio de Oquendo">

                            <img class="img-fluid" src="../img/DetalleAlojamiento2.png" alt="interior alojamiento Palacio de Oquendo">
                        </div>
                        <div class="col-8">
                            <div class="position-relative">
                                <img class="img-fluid" src="../img/DetalleAlojamiento3.png" alt="fachada alojamiento Palacio de Oquendo">
                                <div class="col-6 offset-6 textoEnImagen" >
                                    <div class="row bg-light textoEnImagenRow">
                                        <div class="col-8 text-end puntuacionTexto">
                                            <b>Fabuloso</b><br>
                                            <div class="comentarios">
                                                4.592 comentarios
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <p class="bg-primary text-light px-1 rounded fw-bold puntuacionMedia">8,7</p>
                                        </div>
                                    </div>

                                    <div class="row mt-2 bg-light textoEnImagenRow">
                                        <div class="col ">
                                            <span class="texto-default">La ubicación es muy buena. El servicio y la habitación excelente.</span>
                                        </div>
                                    </div>
                                    <div class="row mt-2 bg-light textoEnImagenRow py-2">
                                        <div class="col-9 pt-2">
                                            <span class="texto-default"><b>¡Excelente ubicación!</b></span>
                                        </div>
                                        <div class="col-3">
                                            <p class="border border-2 border-dark rounded puntuacionMedia fw-bold">9,6</p>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row ms-1 mt-2">
                        <div class="col-3">
                            <img class="img-fluid" src="../img/DetalleAlojamiento8.png" alt="entrada alojamiento Palacio de Oquendo">
                        </div>
                        <div class="col-3">
                            <img class="img-fluid" src="../img/DetalleAlojamiento5.png" alt="cuarto de baño alojamiento Palacio de Oquendo">
                        </div>
                        <div class="col-3">
                            <img class="img-fluid" src="../img/DetalleAlojamiento6.png" alt="habitación alojamiento Palacio de Oquendo">
                        </div>
                        <div class="col-3">
                            <img class="img-fluid" src="../img/DetalleAlojamiento7.png" alt="fachada alojamiento Palacio de Oquendo">
                        </div>
                    </div>
                </div>
            </div> <!-- Termina la row principal de la izquierda -->

            <div class="container-fluid">
                <div class="row">
                    <div class="col border me-2 py-2">
                        <i class="fa-regular fa-eye"></i>
                        <span>Vistas</span>
                    </div>
                    <div class="col border me-2 py-2">
                        <i class="fa-solid fa-wifi"></i>
                        <span>Wifi gratis</span>
                    </div>
                    <div class="col border me-2 py-2">
                        <i class="fa-solid fa-umbrella-beach"></i>
                        <span>Balcón</span>
                    </div>
                    <div class="col border me-2 py-2">
                        <i class="fa-solid fa-paw"></i>
                        <span>Admite mascotas</span>
                    </div>
                    <div class="col border py-2">
                        <i class="fa-solid fa-bath"></i>
                        <span>Bañera</span>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row my-2">
                    <div class="col border me-2 py-2">
                        <i class="fa-regular fa-snowflake"></i>
                        <span>Aire acondicionado</span>
                    </div>
                    <div class="col border me-2 py-2">
                        <i class="fa-regular fa-clock"></i>
                        <span>Recepción 24 horas</span>
                    </div>
                    <div class="col border me-2 py-2">
                        <i class="fa-solid fa-broom"></i>
                        <span>Servicio de limpieza diario</span>
                    </div>
                    <div class="col border py-2">
                        <i class="fa-solid fa-check"></i>
                        <span>Tarjeta de acceso</span>

                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-8 mt-3">
                    <p class="texto-default">
                    ¡Puedes conseguir un descuento Genius en NH Collection Cáceres Palacio de Oquendo! Para ahorrar en este alojamiento, solo tienes que <a href="../inicioSesion/index.html">iniciar sesión.</a>
                    </p>
                    <p class="texto-default">
                    Este precioso palacio reconvertido del siglo XVI se encuentra en el centro histórico de Cáceres, junto a las antiguas murallas romanas y árabes de la ciudad. El alojamiento ofrece habitaciones elegantes con aire acondicionado, WiFi gratuita y acceso gratuito a la sala de fitness.
                    </p>
                    <p class="texto-default">
                    Las habitaciones del NH Collection Cáceres Palacio de Oquendo son amplias, presentan una decoración moderna y disponen de TV de 48 pulgadas, cafetera, hervidor eléctrico con infusiones gratuitas y carta de almohadas. También incluyen baño con ducha de efecto lluvia y secador de pelo profesional.
                    </p>
                    <p class="texto-default">
                    El restaurante del hotel alberga una terraza en la plaza de San Juan y sirve tapas locales tradicionales y especialidades clásicas españolas, así como cocina internacional. El bar ocupa las antiguas caballerizas y ofrece vistas a la plaza.
                    </p>
                    <p class="texto-default">
                    El alojamiento está situado a solo 100 metros del centro histórico de Cáceres, declarado Patrimonio de la Humanidad por la UNESCO, y de la iglesia de San Juan. El establecimiento se halla a 10 minutos a pie de la plaza de toros.
                    </p>
                    <p class="texto-default">
                    A las parejas les encanta la ubicación — Le han puesto un <b>9,6</b> para viajes de dos personas.
                    </p>
                    <p class="negrita mt-5">Servicios más populares</p>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col border me-1">
                                <i class="fa-solid fa-dumbbell"></i>
                                <span>Gimnasio</span>
                            </div>
                            <div class="col border me-1">
                                <i class="fa-solid fa-martini-glass-citrus"></i>
                                <span>Bar</span>
                            </div>
                            <div class="col border me-1">
                                <i class="fa-solid fa-wifi"></i>
                                <span>Wifi gratis</span>
                            </div>
                            <div class="col border">
                                <i class="fa-solid fa-utensils"></i>
                                <span>Restaurante</span>
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col border me-1">
                                <i class="fa-solid fa-square-parking"></i>
                                <span>Parking</span>
                            </div>
                            <div class="col border me-1">
                                <i class="fa-solid fa-mug-saucer"></i>
                                <span>Muy buen desayuno</span>
                            </div>
                            <div class="col border">
                                <i class="fa-solid fa-elevator"></i>
                                <span>Ascensor</span>
                            </div>

                        </div>

                    </div>


                </div> <!-- Termina col-8 (texto) -->

                <div class="col-4 mt-3">
                    <div class="row">
                        <div class="col-5 border">
                            <span class="texto-default text-secondary">Marca/cadena de hotel</span> 
                            <p class="texto-default negrita">NH Collection</p>
                        </div>
                        <div class="col-7 border">
                            <img src="../img/DetalleAlojamiento9.png" class="img-fluid" alt="logo NH Collection">
                        </div>
                    </div>

                    <div class="row my-1 bg-custom-light-blue">
                        <div class="col-12 pt-2">
                            <p class="negrita">Puntos fuertes del alojamiento</p>
                            <p class="negrita texto-default">¡Ideal para estancias de dos noches!</p>
                            <div class="row">
                                <div class="col-1 mt-1">
                                    <i class="fa-solid fa-location-dot"></i>
                                </div>
                                <div class="col-11">
                                    <span class="texto-default">Situado en la zona mejor valorada de Cáceres, este hotel tiene una excelente puntuación en ubicación (9,6). </span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-1 mt-1">
                                    <i class="fa-solid fa-bed"></i>
                                </div>
                                <div class="col-11">
                                    <span class="texto-default">¿Quieres dormir a pierna suelta? Este hotel tiene una puntuación muy alta por la comodidad de sus camas.</span>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="row my-1 bg-custom-light-blue">
                        <div class="col-12 pt-2">
                            <p class="negrita">Habitaciones con:</p>
                            <div class="row">
                                <div class="col-1 mt-1">
                                    <i class="fa-solid fa-tree"></i>
                                </div>
                                <div class="col-11">
                                    <span class="texto-default">Vistas a un patio interior.</span>
                                </div>
                            </div>

                            <p class="negrita pt-2">Nivel 3+ de Travel Sustainable</p>
                            <div class="row">
                                <div class="col-1 mt-1">
                                    <i class="fa-solid fa-award"></i>
                                </div>
                                <div class="col-11">
                                    <span class="texto-default">Este alojamiento dispone de certificaciones de sostenibilidad de terceros. <a href="">Más info.</a></span>
                                </div>
                            </div>
                        </div>
                        <a href="../carrito/index.html" class="btn btn-primary border-0 text-truncate mt-4">
                            Reserva ahora
                        </a>

                    </div>

                </div>
            </div> <!-- Termina la segunda row principal (texto y bg azul)-->

            <div class="row border my-5 py-3">
                <div class="col-10 mt-2">
                    <p class="negrita fs-5">Inicia sesión y ahorra</p>
                    <p>Podrías ahorrar un 20% o más en este alojamiento al iniciar sesión. </p>
                    <div class="row">
                        <div class="col-2">
                            <!-- <a href="../inicioSesion/index.html"> -->
                            <!--     <button class="btn btn-light border border-3 text-primary">Inicia sesión</button> -->
                            <!-- </a> -->

                            <a href="../inicioSesion/index.html" class="btn btn-light text-primary border border-3">
                                Inicia sesión
                            </a>
                        </div>
                        <div class="col-2 mt-2">
                            <a href="../Registro/index.html">Hazte una cuenta</a>
                        </div>
                    </div>
                </div>
                <div class="col-2">
                    <img src="../img/DetalleAlojamiento10.png" class="img-fluid" alt="ahorro por iniciar sesión">
                </div>
            </div>


            <h3 class="negrita">Disponibilidad</h3>

            <form action="index.html">
                <div class="row mb-5">
                    <div class="col-3 border border-warning border-3 rounded">
                        <input type="date" class=" form-control disponibilidad border-0" required>
                    </div>
                    <div class="col-5 border border-warning border-3 rounded">
                        <div class="row">
                            <div class="col-4">
                                <input type="number" class=" form-control disponibilidad border-0" placeholder="Adultos -" min="0" required>
                            </div>
                            <div class="col-4">
                                <input type="number" class=" form-control disponibilidad border-0" placeholder="Niños -" min="0" required>
                            </div>
                            <div class="col-4">
                                <input type="number" class=" form-control disponibilidad border-0" placeholder="Habitaciones" min="0" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <input type="submit" class="btn btn-primary border-0 text-truncate py-2"
                                             id="ModificarBúsqueda" value="Modificar búsqueda">
                    </div>
                </div>
            </form>

            <!-- Tabla disponibilidad -->
            <div class="container-fluid">
                <table class="table border">
                    <thead class=" bg-primary ">
                        <tr>
                            <th class="text-light negrita border col-4">Tipo de alojamiento</th>
                            <th class="text-light negrita border col-1">Número de personas</th>
                            <th class="text-light negrita border col-1">Precio para 2 personas</th>
                            <th class="text-light negrita border col-2">Tus opciones</th>
                            <th class="text-light negrita border">Seleccionar habitaciones</th>
                            <th class="text-light negrita border"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <!-- Tipo de alojamiento -->
                            <td rowspan="2">
                                <p>
                                <a href="" class="negrita">Habitación doble superior - 1 o 2 camas</a>
                                </p>
                                <p class="rojo negrita texto-default">Solo queda 1 habitación en nuestra web</p>
                                <span class="negrita texto-default">Elige tu cama (si hay disponibilidad)</span>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="casilla1">
                                    <label class="form-check-label texto-default" for="casilla1">
                                        <span>2 camas individuales</span>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="casilla2">
                                    <label class="form-check-label texto-default" for="casilla2">
                                        <span>1 cama doble</span>
                                    </label>
                                </div>
                                <div class="container-fluid mt-3 border-bottom border-2">
                                    <div class="row d-inline-flex align-items-center">
                                        <div class="col-auto">
                                            <i class="fa-solid fa-ruler-combined"></i>
                                            <span class="texto-default">25 m²</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-tree"></i>
                                            <span class="texto-default">Vistas al patio</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-martini-glass-citrus"></i>
                                            <span class="texto-default">Minibar</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-regular fa-snowflake"></i>
                                            <span class="texto-default">Aire acondicionado</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-wifi"></i>
                                            <span class="texto-default">Wifi gratis</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="container-fluid mt-3 border-2">
                                    <div class="row d-inline-flex align-items-center">
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Artículos de aseo gratis</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Caja fuerte</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">WC</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Bañera o ducha</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">TV</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Suelo de madera o parquet</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Toallas</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Ropa de cama</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Enchufe cerca de la cama</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Escritorio</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Teléfono</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Canales vía satélite</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Calefacción</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Secador de pelo</span>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fa-solid fa-check verde"></i>
                                            <span class="texto-default">Desinfectante de manos</span>
                                        </div>
                                    </div>
                                </div>

                            </td>
                            <!-- Número de personas -->
                            <td>
                                <i class="fa-solid fa-person"></i>
                                <i class="fa-solid fa-person"></i>
                            </td>
                            <!-- Precio para 2 personas -->
                            <td>
                                <span class="negrita">€ 285</span>
                                <span class="texto-default">Incluye impuestos y cargos</span>
                            </td>
                            <!-- Tus opciones -->
                            <td>
                                <i class="fa-solid fa-mug-saucer"></i>
                                <span class="texto-default">Desayuno <b>muy bueno</b> por € 20.</span>
                                <ul>
                                    <li><b class="texto-default">No reembolsable.</b></li>
                                    <li><b class="texto-default">Pagas por adelantado.</b></li>
                                </ul>
                            </td>
                            <!-- Seleccionar habitaciones -->
                            <td>
                                <input type="number" name="cantidad" min="0" max="100" step="1" value=0 class="mt-2">
                            </td>
                            <!-- Botón reservaré -->
                            <td rowspan="2">
                                <!-- <button type="submit" class="btn btn-primary btn-block border-0 my-3" id="reservaré"> -->
                                <!--     Reservaré -->
                                <!-- </button> -->

                                <a href="../carrito/index.html" class="btn btn-primary btn-block border-0 text-truncate my-3" id="reservaré">
                                    Reservaré
                                </a>
                                <ul>
                                    <li>
                                        <p class="texto-default">Confirmación inmediata</p>
                                    </li>
                                </ul>
                            </td>
                        </tr>

                        <!-- ############################################ -->
                        <!-- Segunda fila -->
                        <!-- ############################################ -->
                        <tr>
                            <!-- Número de personas -->
                            <td>
                                <i class="fa-solid fa-person"></i>
                                <i class="fa-solid fa-person"></i>
                            </td>
                            <!-- Precio para 2 personas -->
                            <td>
                                <span class="negrita">€ 294</span>
                                <span class="texto-default">Incluye impuestos y cargos</span>
                            </td>
                            <!-- Tus opciones -->
                            <td>
                                <i class="fa-solid fa-mug-saucer"></i>
                                <span class="texto-default">Desayuno <b>muy bueno</b> por € 20.</span><br>
                                <i class="fa-solid fa-check verde"></i>
                                <span class="texto-default verde"><b>Cancelación gratis</b> antes del 25 de enero de 2024.</span>
                            </td>
                            <!-- Seleccionar habitaciones -->
                            <td>
                                <input type="number" name="cantidad" min="0" max="100" step="1" value=0 class="mt-2">
                            </td>

                        </tr>
                    </tbody>
                </table>
            </div> <!-- finaliza la tabla de disponibilidad -->
        </div> <!-- Termina el container principal -->



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    </body>
</html>
