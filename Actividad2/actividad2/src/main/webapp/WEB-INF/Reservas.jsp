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
	href="${pageContext.request.contextPath}/css/Reserva.css">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
</head>

<body>
    <%@ include file="Cabecera.jsp" %>

    <div class="container mb-5">
        <div class="row mt-3">
            <div class="col-3 offset-2">
                <h3 class="fw-bold">Reservas y viajes</h3>
            </div>
        </div>
        <c:forEach var="bookingPropertyInfo" items="${bookingsProperties}">
            <div class="row mt-4">
                <div class="col-8 offset-2">
                    <h4 class="fw-bold">${bookingPropertyInfo.property.city}</h4>
                    <fieldset class="shadow rounded">
                        <div class="row py-3 ps-2">
                            <div class="col-2 ps-4">
                                <img class="rounded" src="images/DetalleAlojamiento3.png" alt="foto ${bookingPropertyInfo.property.name}">
                            </div>
                            <div class="col ms-5">
                                <p>
                                    <span class="fw-bold">${bookingPropertyInfo.property.name}</span>
                                </p>
                                <p>${bookingPropertyInfo.property.address}</p>
                                <p>${bookingPropertyInfo.property.description}</p>
                                
                            </div>
                            <div class="col text-end pe-5">
                                <p class="fw-bold fs-5 precio mt-5">Precio: ${bookingPropertyInfo.booking.totalPrice}€</p>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </c:forEach>
    </div>
</body>


</html>
