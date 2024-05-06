angular.module('bookingApp')
	.factory("propertiesFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/properties/';
		var propertiesInterface = {
			
			//get all properties
			getProperties: function(){
				return $http.get(url)
				.then(function(response){
					return response.data;
				});
			}, 
			
			//get property by id
			getProperty: function(id) {
				return $http.get(url + "2/" + id)
					.then(function(response) {
						return response.data;
					});
			},
			
			//get property by search
			getPropertyBySearch: function(search) {
				return $http.get(url + search)
					.then(function(response) {
						return response.data;
					});
			},
			
		
			//create a new property
			postProperty: function(property) {
				return $http.post(url, property)
					.then(function(response) {
						return response.data;
					});
			},
			
			//update a property
			putProperty: function(property) {
				return $http.put(url + property.id, property)
					.then(function(response) {
						return response.status;
					});
			},
			
			//delete a property
			deleteProperty: function(id) {
				return $http.delete(url + id)
					.then(function(response) {
						return response.status;
					});
			}
		}
		return propertiesInterface;
	}])