<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">

    <head>
        <title>Trending destinations</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                                                                                             integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Trending.css">
    </head>

    <body>

        <%@ include file="Cabecera.jsp" %>

        <div class="container mb-5">
            <div class="row">
                <div class="col-10 offset-1">
                    <h2 class="fw-bold">Destinos de moda</h2>
                    <p>Opciones más populares para los viajeros en España</p>

                    <div class="row">
                        <div class="col-6">
                            <img class="rounded img-fluid" src="images/madrid.jpg" alt="foto Madrid">
                            <div class="ciudad text-center ms-2 mt-1">
                                <p class="fw-bold fs-5 text-light">Madrid</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <img class="rounded img-fluid" src="images/sevilla.jpg" alt="foto Sevilla">
                            <div class="ciudad text-center ms-2 mt-1">
                                <p class="fw-bold fs-5 text-light">Sevilla</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <img class="rounded img-fluid" src="images/barcelona.jpg" alt="foto Barcelona">
                            <div class="ciudad text-center ms-2 mt-1">
                                <p class="fw-bold fs-5 text-light">Barcelona</p>
                            </div>
                        </div>
                        <div class="col-4">
                            <img class="rounded img-fluid" src="images/valencia.jpg" alt="foto Valencia">
                            <div class="ciudad text-center ms-2 mt-1">
                                <p class="fw-bold fs-5 text-light">Valencia</p>
                            </div>
                        </div>
                        <div class="col-4">
                            <img class="rounded img-fluid" src="images/granada.jpg" alt="foto Granada">
                            <div class="ciudad text-center ms-2 mt-1">
                                <p class="fw-bold fs-5 text-light">Granada</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-10 offset-1">
                    <h2 class="fw-bold">Explora España</h2>
                    <p>Estos populares destinos tienen mucho que ofrecer</p>
                    <div class="row">
                        <div class="col-2">
                            <img class="rounded img-fluid" src="images/madrid.jpg" alt="foto Madrid">
                            <p class="fw-bold mt-2">Madrid</p>
                            <p>3,000 propiedades</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/sevilla.jpg" alt="foto Sevilla">
                            <p class="fw-bold mt-2">Sevilla</p>
                            <p>1,500 propiedades</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/barcelona.jpg" alt="foto Barcelona">
                            <p class="fw-bold mt-2">Barcelona</p>
                            <p>2,000 propiedades</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/valencia.jpg" alt="foto Valencia">
                            <p class="fw-bold mt-2">Valencia</p>
                            <p>1,000 propiedades</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/cordoba.jpg" alt="foto Córdoba">
                            <p class="fw-bold mt-2">Cordoba</p>
                            <p>1,200 propiedades</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/malaga.jpg" alt="foto Málaga">
                            <p class="fw-bold mt-2">Málaga</p>
                            <p>1,800 propiedades</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-10 offset-1">
                    <h2 class="fw-bold">Busca en Barcelona por tipo de propiedad</h2>
                    <div class="row mt-3">
                        <div class="col">
                            <img class="rounded img-fluid" src="images/hotel.jpeg" alt="foto ejemplo hotel">
                            <p class="fw-bold mt-2">Hoteles</p>
                            <p>6 Mar-15 Mar, 2 adultos</p>
                            <p>301 disponibles</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/apartamento.jpg" alt="foto ejemplo apartamento">
                            <p class="fw-bold mt-2">Apartamentos</p>
                            <p>6 Mar-15 Mar, 2 adultos</p>
                            <p>445 disponibles</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/villa.jpg" alt="foto ejemplo villa">
                            <p class="fw-bold mt-2">Villas</p>
                            <p>6 Mar-15 Mar, 2 adultos</p>
                            <p>2 disponibles</p>
                        </div>
                        <div class="col">
                            <img class="rounded img-fluid" src="images/holidays.jpg" alt="foto ejemplo casa de vacaciones">
                            <p class="fw-bold mt-2">Casas de vacaciones</p>
                            <p>6 Mar-15 Mar, 2 adultos</p>
                            <p>9 disponibles</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row destinations">
                <div class="col-10 offset-1">

                    <h2 class="fw-bold mb-4">Destinos que amamos</h2>
                    <button type="button" class="btn fw-bold rounded-5 mb-3" onclick="mostrarInfo('info1')">Regiones</button>
                    <button type="button" class="btn fw-bold rounded-5 mb-3" onclick="mostrarInfo('info2')">Ciudades</button>
                    <button type="button" class="btn fw-bold rounded-5 mb-3" onclick="mostrarInfo('info3')">Lugares de interés</button>

                    <table id="info1" class="info table table-borderless">
                        <tr>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>7 propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Regiones</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                    </table>

                    <table id="info2" class="info table table-borderless">
                        <tr>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Ciudad</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                    </table>

                    <table id="info3" class="info table table-borderless">
                        <tr>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                            <td>
                                <p class="fw-bold">Lugar</p>
                                <p>x propiedades</p>
                            </td>
                        </tr>
                    </table>


                    <script>
                                   function mostrarInfo(id) {
                                   var info = document.getElementById(id);

                                   // Ocultar todas las otras informaciones
                                   var infos = document.getElementsByClassName('info');
                                   for (var i = 0; i < infos.length; i++) {
                                   infos[i].style.display = 'none';
                                   }

                                   // Mostrar la información correspondiente al botón
                                   info.style.display = 'block';
                                   }
                    </script>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                crossorigin="anonymous"></script>
    </body>

</html>
