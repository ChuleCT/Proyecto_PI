angular.module('bookingApp')
	.factory("bookingsFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/bookings/';
		var bookingsInterface = {

			getBookings: function() {
				return $http.get(url)
					.then(function(response) {
						return response.data;
					});
			},

			getProvisionalBookings: function() {
				return $http.get(url + 'provisional')
					.then(function(response) {
						return response.data;
					});
			},
			
			//Obtiene una propiedad a partir del id de una habitación
			getProperty: function(ida) {
				return $http.get(url + ida)
					.then(function(response) {
						return response.data;
					});
			},
			
			
			
			
			//Para calcular el precio total de la reserva me creo un mapa quie tenga como key la habitacion que saque de accommodations y el numero de habitaciones que se quieren reservar lo saco de bookings.num 
			// posteriormete recorro el mapa y voy sumando el precio de cada habitacion por el numero de habitaciones que se quieren reservar
			
			


			postProvisionalBooking: function(ida, numAccommodations) {
				return $http.post(url + 'provisional/' + ida, numAccommodations)
					.then(function(response) {
						return response.data;
					});
			},
			
			deleteProvisionalBooking: function(ida) {
				return $http.delete(url + 'provisional/' + ida)
					.then(function(response) {
						return response.data;
					});
			},
			
			deleteAllProvisionalBookings: function() {
				return $http.delete(url + 'provisional')
					.then(function(response) {
						return response.data;
					});
			},

		}

		return bookingsInterface;
	}])