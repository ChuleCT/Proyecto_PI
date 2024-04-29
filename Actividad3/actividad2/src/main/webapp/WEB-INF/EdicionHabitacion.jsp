<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">

<head>
<title>${requestScope.CheckType}</title>
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
    
	   <%@ include file="Cabecera.jsp" %>
    
	    <div class="container">
		    <div class="row">
			    <div class="col-8 mx-auto">
				    <h1 class="mb-3">${requestScope.CheckType}</h1>
				    <h4 class="mb-2 text-danger">${messages.error}</h4>
				    <form method="post" action="?" class="pb-5">
					    <input type="hidden" name="id" value="${accommodation.id}"> 
                        <input type="hidden" name="idp" value="${idp}">
					    <div class="mb-3">
                            <label for="nombreHabitacion" class="form-label">Nombre de la habitación</label>
                            <input type="text" class="form-control" id="name" name="name" value="${accommodation.name}" required>
					    </div>
					    <div class="mb-3">
						    <label for="precio" class="form-label">Precio</label>
                            <input type="number" class="form-control" id="price" name="price" step="1" min="0" max="999999999" value="${accommodation.price}" required>
					    </div>
					    <div class="mb-3">
						    <label for="descripcion" class="form-label">Descripción</label> 
                            <input type="text" class="form-control" id="description" name="description" value="${accommodation.description}" required>
					    </div>
					    <div class="mb-3">
						    <label for="precio" class="form-label">Número de habitaciones disponibles</label>
                            <input type="number" class="form-control" id="numAccommodations" name="numAccommodations" step="1" min="0" max="999999999" value="${accommodation.numAccommodations}" required>
					    </div>
    
    
					    <input type="submit" class="form-control btn btn-primary col-12"
						    value="Guardar">
				    </form>
			    </div>
		    </div>
	    </div>
    
    

    </body>

</html>