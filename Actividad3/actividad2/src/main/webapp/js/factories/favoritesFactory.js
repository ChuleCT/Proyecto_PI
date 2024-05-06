angular.module('bookingApp')
    .factory("favoritesFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/favorites/';
		var favoritesInterface = {
			
			getFavorites: function() {
				return $http.get(url)
					.then(function(response) {
						return response.data;
					})
			},
			postFavorite: function(propertyId) {
				return $http.post(url, propertyId)
					.then(function(response) {
						return response.data;
					})
			},
			deleteFavorite: function(propertyId) {
				return $http.delete(url + propertyId)
					.then(function(response) {
						return response.data;
					})
			}
		};

		return favoritesInterface;
	}
]);
