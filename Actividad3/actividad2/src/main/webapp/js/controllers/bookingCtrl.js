angular.module('bookingApp')
	.controller('bookingCtrl', ['bookingsFactory', 'accommodationsFactory', '$routeParams', '$location', '$window', '$scope',
		function(bookingsFactory, accommodationsFactory, $routeParams, $location, $window, $scope) {

			var bookingVM = this;
			bookingVM.bookings = {};
			bookingVM.property = {}; // solo para el carrito de compras
			bookingVM.totalPrice = 0;
			bookingVM.provisionalBookings = {}; // Son objetos que constan de un ida, un num, un idp y un price 
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
							// Cambio: Almacena directamente el mapa de reservas provisionales
							bookingVM.provisionalBookings = response;
							console.log("Provisional bookings: ", bookingVM.provisionalBookings);

							if (bookingVM.provisionalBookings.length > 0) {
								bookingVM.functions.getProperty();
								bookingVM.functions.calculateTotalPrice();
							}
						}, function(error) {
							console.log(error);
						});
				},

				// getProperty: function(ida) {
				getProperty: function() {
					bookingsFactory.getProperty(bookingVM.provisionalBookings[0].idp)
						.then(function(response) {
							bookingVM.property = response;
						}, function(error) {
							console.log(error);
						});
				},

				// Para el precio total solo hay que sumar los precios de todas las habitaciones
				calculateTotalPrice: function() {
					bookingVM.totalPrice = 0;
					for (var i = 0; i < bookingVM.provisionalBookings.length; i++) {
						bookingVM.totalPrice += bookingVM.provisionalBookings[i].price;
					}
				},

				deleteBooking: function(ida) {
					console.log("Deleting booking with ida: ", ida);
					bookingsFactory.deleteProvisionalBooking(ida)
						.then(function(response) {
							$window.location.reload();
						}, function(error) {
							console.log(error);
						});
				},

				confirmBooking: function() {
					console.log("Confirming booking");
					bookingsFactory.confirmBooking()
						.then(function(response) {
							bookingsFactory.deleteAllProvisionalBookings()
								.then(function(response) {
									console.log("Provisional bookings deleted");
								}, function(error) {
									console.log(error);
								});
						}, function(error) {
							console.log(error);
						});
					$location.path('/myBookings');
				}
			}


			$scope.isEmpty = function(obj) {
				return Object.keys(obj).length === 0;
			}

			if (bookingVM.functions.where('/myBookings')) {
				bookingVM.functions.getBookings();
			} else if (bookingVM.functions.where('/shoppingCart')) {
				bookingVM.functions.getProvisionalBookings();
			}
		}
	]);
