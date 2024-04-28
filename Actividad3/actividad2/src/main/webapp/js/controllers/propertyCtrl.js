angular.module('bookingApp')
	.controller('propertyCtrl', ['usersFactory', 'propertiesFactory', 'servicesFactory','$routeParams', '$location', 
	function (usersFactory, propertiesFactory, servicesFactory, $routeParams, $location) {
		
		var propertyVM = this;
		propertyVM.property = {};
		propertyVM.search = "";
		propertyVM.servicesChecked = [];
		propertyVM.allServices = [];
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
						console.log("Property: ", propertyVM.property);
						
						// Obtener servicios asociados a la propiedad seleccionada
						servicesFactory.getServicesByPropertyId(id)
							.then(function(response) {
								propertyVM.servicesChecked = response;
								console.log("Services: ", propertyVM.servicesChecked);
							});
						
						            // Obtener todos los servicios disponibles
            			servicesFactory.getAllServices()
                			.then(function(response) {
                    			propertyVM.allServices = response;
                    			console.log("All Services: ", propertyVM.allServices);
                			});

					});
			},
			isServiceChecked: function(service) {
				// Itera sobre la lista de servicios marcados y verifica si el servicio está presente
				for (var i = 0; i < propertyVM.servicesChecked.length; i++) {
					if (propertyVM.servicesChecked[i].name === service.name) {
						return true; 
					}
				}
				return false; 
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
			propertyHandlerMethod: function() {
				if (propertyVM.functions.where('/myProperties')) {
					propertyVM.functions.getPropertiesByUser();
				} else if (propertyVM.functions.where('/createProperty')) {
					propertyVM.property = {};
				} else if (propertyVM.functions.where('/editProperty/'+propertyVM.property.id)) {
					
					console.log("Property ID: ", $routeParams.ID);
					propertyVM.functions.updateProperty();
				} else if (propertyVM.functions.where('/properties/'+propertyVM.search)) {
					propertyVM.functions.getPropertiesBySearch(propertyVM.search);
				} else if (propertyVM.functions.where('/deleteProperty/'+propertyVM.property.id)) {
					propertyVM.functions.deleteProperty(propertyVM.property.id);
				}
			},
			
		}
		
		
		if (propertyVM.functions.where('/editProperty/' + $routeParams.ID)) {
			propertyVM.functions.getProperty($routeParams.ID);
		} else {
			propertyVM.functions.propertyHandlerMethod();
		}
		

		
		}]);