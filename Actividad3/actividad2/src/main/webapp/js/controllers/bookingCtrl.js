angular.module('bookingApp')
	.controller('bookingCtrl', ['bookingsFactory', 'accommodationsFactory', '$routeParams', '$location',
		function(bookingsFactory, accommodationsFactory, $routeParams, $location) {

			var bookingVM = this;
			bookingVM.bookings = {};
			bookingVM.accommodations = {};
			bookingVM.property = {}; // solo para el carrito de compras
			bookingVM.functions = {

				where: function(route) {
					return $location.path() == route;
				},

				getBookings: function() {
					bookingsFactory.getBookings()
						.then(function(response) {
							bookingVM.bookings = response;
						}, function(error) {
							console.log(error);
						});
				},

				getProvisionalBookings: function() {
					bookingsFactory.getProvisionalBookings()
						.then(function(response) {
							bookingVM.bookings = response;
							//Por cada reserva, utilizo el ida para obtener la habitacion a la que se refiere y las voy añadiendo a accommodations
							for (var i = 0; i < bookingVM.bookings.length; i++) {
								accommodationsFactory.getAccommodation(bookingVM.bookings[i].ida)
									.then(function(response) {
										bookingVM.accommodations.push(response);
									}, function(error) {
										console.log(error);
									});
							}
						}, function(error) {
							console.log(error);
						});
				},

				// Obtengo la propiedad que se quiere reservar, para ello uso el parametro "ida" de la primera habitacion de bookingVM.bookings
				getProperty: function() {
					bookingsFactory.getProperty(bookingVM.bookings[0].ida)
						.then(function(response) {
							bookingVM.property = response;
						}, function(error) {
							console.log(error);
						});
				},

			}

			if (bookingVM.functions.where('/myBookings')) {
				bookingVM.functions.getBookings();
			} else if (bookingVM.functions.where('/shoppingCart')) {
				bookingVM.functions.getProvisionalBookings();
				bookingVM.functions.getProperty();
			}


		}]);