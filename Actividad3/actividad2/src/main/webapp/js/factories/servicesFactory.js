angular.module('bookingApp')
	.factory("servicesFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/services/';

		var servicesInterface = {
			// Obtener todos los servicios
			getAllServices: function() {
				return $http.get(url)
					.then(function(response) {
						return response.data;
					});
			},
			// Obtener servicios asociados a una propiedad específica
			getServicesByPropertyId: function(propertyId) {
				return $http.get(url + 'p/' + propertyId)
					.then(function(response) {
						return response.data;
					});
			},

			putServices: function(propertyId, services) {
				return $http.put(url + propertyId, services)
					.then(function(response) {
						return response.data;
					});
			},
			postServices: function(propertyId, services) {
				return $http.post(url + propertyId, services)
					.then(function(response) {
						return response.data;
					});
			},


		};
		return servicesInterface;
	}]);