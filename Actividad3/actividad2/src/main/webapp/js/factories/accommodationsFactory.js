angular.module('bookingApp')
	.factory("accommodationsFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/accommodations/';
		var accommodationsInterface = {

			//get all accommodations of a property
			getAccommodations: function(id) {
				return $http.get(url + id)
					.then(function(response) {
						return response.data;
					});
			},
			
			// get an accommodation by id
			getAccommodation: function(id) {
				return $http.get(url + "2/" + id)
					.then(function(response) {
						return response.data;
					});
			},
			
			// add an accommodation
			postAccommodation: function(accommodation) {
				return $http.post(url, accommodation)
					.then(function(response) {
						return response.status;
					});
			},
			
			// update an accommodation
			putAccommodation: function(accommodation) {
				return $http.put(url + accommodation.id, accommodation)
					.then(function(response) {
						return response.status;
					});
			},
			
			// delete an accommodation
			deleteAccommodation: function(id) {
                return $http.delete(url + id)
                    .then(function(response) {
                        return response.status;
                    });
            }
		}
	}])