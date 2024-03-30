<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">
    
    <head>
        <title>Edición de habitación</title>
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
    
	    <!-- navbar -->
	    <div class="container-fluid color-navbar pt-3">
		    <div class="row mb-5" id="navbar">
			    <div class="col-4 offset-2">
				    <a class="navbar-brand text-light fs-4 fw-bold"
					    href="BusquedaServlet.do">Booking.com</a>
			    </div>
			    <div class="col-1 offset-1">
				    <a href="../Registro/index.html" class="btn btn-light text-primary">
					    Registrarse </a>
			    </div>
			    <div class="col-2">
				    <a href="../inicioSesion/index.html"
					    class="btn btn-light text-primary"> Iniciar Sesión </a>
			    </div>
		    </div>
	    </div>
    
	    <div class="container">
		    <div class="row">
			    <div class="col-8 mx-auto">
				    <h1 class="mb-3">Edición de habitación</h1>
				    <form method="post" action="?" class="pb-5">
					    <input type="hidden" name="id" value="${accommodation.id}"> 
                        <input type="hidden" name="idp" value="${accommodation.idp}">
					    <div class="mb-3">
                            <label for="nombreHabitacion" class="form-label">Nombre de la habitación</label>
                            <input type="text" class="form-control" id="name" name="name" value="${accommodation.name}" required>
					    </div>
					    <div class="mb-3">
						    <label for="precio" class="form-label">Precio</label>
                            <input type="number" class="form-control" id="price" name="price" step="1" min="0" value="${accommodation.price}" required>
					    </div>
					    <div class="mb-3">
						    <label for="descripcion" class="form-label">Descripción</label> 
                            <input type="text" class="form-control" id="description" name="description" value="${accommodation.description}" required>
					    </div>
					    <div class="mb-3">
						    <label for="precio" class="form-label">Número de habitaciones disponibles</label>
                            <input type="number" class="form-control" id="numAccommodations" name="numAccommodations" step="1" min="0" value="${accommodation.numAccommodations}" required>
					    </div>
    
    
					    <input type="submit" class="form-control btn btn-primary col-12"
						    value="Guardar">
				    </form>
			    </div>
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