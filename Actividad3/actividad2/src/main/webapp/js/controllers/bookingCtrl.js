angular.module('bookingApp')
	.controller('bookingCtrl', ['bookingsFactory', '$routeParams', '$location',
		function(bookingsFactory, $routeParams, $location) {

			var bookingVM = this;
			bookingVM.bookings = {};
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

			}
			
			bookingVM.functions.getBookings();

		}]);