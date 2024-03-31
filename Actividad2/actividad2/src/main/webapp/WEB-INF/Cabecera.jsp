<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <!-- Bootstrap CSS v5.2.1 -->
        <link
                href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
                rel="stylesheet"
                integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
                crossorigin="anonymous">

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
    
                        <div class="col-1 offset-1">
                            <a href="ShoppingCartServlet.do" class="btn btn-light ms-4 fw-bold"><i class="fa-solid fa-cart-shopping"></i> Carrito </a>
                        </div>
                        <div class="col-1">
                            <div class="btn-group">
                                <button type="button" class="btn btn-light dropdown-toggle fw-bold" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${user.name}
                                </button>
                                <div class="dropdown-menu bg-secondary">
                                    <a class="dropdown-item fw-bold" href="<c:url value='EditUserServlet.do?id=${user.id}'/>"><i class="fa-solid fa-user"></i>Mi perfil</a>
                                    <a class="dropdown-item fw-bold" href="ListaAlojamientosServlet.do"><i class="fa-solid fa-house"></i> Mis alojamientos</a>
                                    <a class="dropdown-item fw-bold" href="BookingsServlet.do"><i class=""></i> Mis reservas</a>
                                    <a class="dropdown-item fw-bold" href="FavoritesPropertiesServlet.do"><i class="fa-solid fa-heart"></i> Mis favoritos</a>
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