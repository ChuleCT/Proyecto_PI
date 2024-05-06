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
		}
		
		return bookingsInterface;
	}])