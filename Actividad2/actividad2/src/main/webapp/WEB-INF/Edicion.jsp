<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">

    <head>
        <title>Edición de Alojamiento</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                                                                                             integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Edicion.css">

    </head>

    <body>

        <!-- navbar -->
        <div class="container-fluid color-navbar pt-3">
            <div class="row mb-5" id="navbar">
                <div class="col-4 offset-2">
                    <a class="navbar-brand text-light fs-4 fw-bold" href="../Busqueda/index.html">Booking.com</a>
                </div>
                <div class="col-1 offset-1">
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
            <div class="row">
                <div class="col-8 mx-auto">
                    <h1 class="mb-3">Edición de Alojamiento</h1>
                    <form method="post" action="" class="pb-5">
                        <div class="mb-3">
                            <label for="nombreAlojamiento" class="form-label">Nombre del Alojamiento</label>
                            <input type="text" class="form-control" id="nombreAlojamiento" name="nombreAlojamiento"
                                                                                           required>
                        </div>
                        <div class="mb-3">
                            <label for="direccion" class="form-label">Dirección</label>
                            <input type="text" class="form-control" id="direccion" name="direccion" required>
                        </div>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Teléfono</label>
                            <input type="tel" class="form-control" id="telefono" name="telefono" required>
                        </div>
                        <div class="mb-3">
                            <label for="distanciaCentro" class="form-label">Distancia al centro</label>
                            <input type="number" class="form-control" id="distanciaCentro" name="distanciaCentro" min="0"
                                                                                                                  required>
                        </div>
                        <div class="mb-3">
                            <label for="valoracionMedia" class="form-label">Valoración media</label>
                            <input type="number" class="form-control" id="valoracionMedia" name="valoracionMedia" step="any"
                                                                                                                  min="0" required>
                        </div>
                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                        </div>

                        <div class="mb-3">
                            <p>Servicios o instalaciones ofrecidos</p>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="wifi" id="wifi" name="servicios[]">
                                <label class="form-check label" for="wifi">Wifi</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="piscina" id="piscina"
                                                                                                name="servicios[]">
                                <label class="form-check label" for="piscina">Piscina</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="spa" id="spa" name="servicios[]">
                                <label class="form-check label" for="spa">Spa</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="gimnasio" id="gimnasio"
                                                                                                 name="servicios[]">
                                <label class="form-check label" for="gimnasio">Gimnasio</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="restaurante" id="restaurante"
                                                                                                    name="servicios[]">
                                <label class="form-check label" for="restaurante">Restaurante</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="bar" id="bar" name="servicios[]">
                                <label class="form-check label" for="bar">Bar</label>
                            </div>
                        </div>

                        <div class="mb-3">
                            <p>Permite mascotas</p>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="mascotas" id="si" value="si" required>
                                <label class="form-check label" for="si">Sí</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="mascotas" id="no" value="no" required>
                                <label class="form-check label" for="no">No</label>
                            </div>
                        </div>
                        <!-- <button type="submit" class="btn btn-primary col-12">Guardar</button> -->
                        <input type="submit" class="form-control btn btn-primary col-12" value="Guardar">
                    </form>
                </div>
            </div>
        </div>


        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                crossorigin="anonymous"></script>
    </body>

</html>
