angular.module('bookingApp')
	.controller('propertyCtrl', ['usersFactory', 'propertiesFactory', '$routeParams', '$location', function (usersFactory, propertiesFactory, $routeParams, $location) {
		
		var propertyVM = this;
		propertyVM.property = {};
		propertyVM.functions = {
			
			where : function (route){
				return $location.path() == route;
			},
			
			getPropertiesByUser: function() {
				propertiesFactory.getProperties()
					.then(function(response) {
						propertyVM.property = response;
					});
			},
			
			getProperty: function(id) {
				propertiesFactory.getProperty(id)
					.then(function(response) {
						propertyVM.property = response;
					});
			},
			
			getPropertiesBySearch: function(search) {
				propertiesFactory.getPropertyBySearch(search)
					.then(function(response) {
						propertyVM.property = response;
					});
			},
			
			updateProperty: function() {
				propertiesFactory.putProperty(propertyVM.property)
					.then(function(response) {
						console.log("Updating property, " + response);
					});
			},
			
			createProperty: function() {
				propertiesFactory.postProperty(propertyVM.property)
					.then(function(response) {
						console.log("Creating property, " + response);
					});
			},
			
			deleteProperty: function(id) {
				propertiesFactory.deleteProperty(id)
					.then(function(response) {
						console.log("Deleting property, " + response);
					});
			},
			
			//Habría que hacer ahora el switch para las distintas rutas
			
		}
		
		}]);