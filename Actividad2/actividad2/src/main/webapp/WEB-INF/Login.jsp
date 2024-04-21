<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Iniciar Sesión</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                                                                                             integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/InicioSesion.css">
    </head>

    <body>
        <nav class="navbar mb-5" id="navbar">
				<span class="text-light ms-5 fw-bold fs-4">Booking.com</span>
			</nav>
        <main>
            <div class="container mx-auto my-2">
                <div class="row">
                    <div class="col-5 mx-auto">
                        <h3 class="mb-3">Inicia sesión o crea una cuenta</h3>
                        <form action="LoginServlet.do" method="post">
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" placeholder="Indica tu dirección de email" name="email" id="email" required>
                                <label class="form-label">Password</label>
                                <input type="password" class="form-control" placeholder="Indica tu contraseña" name="password" id="password" required>
                           		<h4 class="text-danger mt-2">${messages}</h4>
                            </div>
                            <input type="submit" class="form-control btn btn-primary col-12 my-3" value="Iniciar sesión">
                        </form>

                        <div class="row text-center">
                            <div class="col-5">
                                <a href="RegistroServlet.do" class="text-primary">¿No estás registrado?</a>
                            </div>
                            <div class="col-6 offset-1">
                                <a href="BusquedaServlet.do" class="text-primary">Acceder sin iniciar sesión</a>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-5 mx-auto">
                        <div class="line mt-5"></div>
                        <p class="text-center">
                        Al iniciar sesión o al crear una cuenta, aceptas nuestros
                        <a href="hola-html"></a><span class="text-primary">Términos y condiciones</span> y la
                        <span class="text-primary">Política de privacidad</span>
                        </p>
                        <div class="line"></div>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <div class="container mx-auto">
                <div class="row">
                    <div class="col-12 text-center">
                        <p class="text-secondary">
                        © 2021 Booking.com. Todos los derechos reservados.
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/  ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                crossorigin="anonymous"></script>
    </body>

</html>
