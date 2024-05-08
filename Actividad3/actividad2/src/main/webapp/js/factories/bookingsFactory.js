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

			postProvisionalBooking: function(ida, numAccommodations) {
				return $http.post(url + 'provisional/' + ida, numAccommodations)
					.then(function(response) {
						return response.data;
					});
			},
			
			confirmBooking: function() {
				console.log("confirmBooking");
                return $http.post(url)
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