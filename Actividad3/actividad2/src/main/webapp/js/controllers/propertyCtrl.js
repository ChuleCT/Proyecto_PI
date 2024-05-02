angular.module('bookingApp')
	.controller('propertyCtrl', ['usersFactory', 'propertiesFactory', 'servicesFactory', '$routeParams', '$location',
		function(usersFactory, propertiesFactory, servicesFactory, $routeParams, $location) {

			var propertyVM = this;
			propertyVM.property = {};
			propertyVM.search = "";
			propertyVM.servicesChecked = [];
			propertyVM.allServices = [];
			propertyVM.functions = {

				where: function(route) {
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
									console.log("getServicesByPropertyId: ", propertyVM.servicesChecked);


									servicesFactory.getAllServices()
										.then(function(response) {
											propertyVM.allServices = response;
											console.log("All Services: ", propertyVM.allServices);

											// Luego de obtener los servicios asociados, llama a initializeCheckedServices
											propertyVM.functions.initializeCheckedServices();
										})
										.catch(function(error) {
											console.error("Error al obtener todos los servicios:", error);
										});

								})
								.catch(function(error) {
									console.error("Error al obtener servicios asociados:", error);
								});
						})
						.catch(function(error) {
							console.error("Error al obtener la propiedad:", error);
						});
				},

				initializeCheckedServices: function() {
					console.log("Services Checked: ", propertyVM.servicesChecked);

					propertyVM.allServices.forEach(function(service) {
						var isChecked = propertyVM.servicesChecked.some(function(checkedService) {
							console.log("Comparing:", service.name, checkedService.name);
							return checkedService.name === service.name;
						});
						service.checked = isChecked;
						console.log("Service:", service.name, " Checked:", service.checked);
					});
				},

				getAllServices: function() {
					servicesFactory.getAllServices()
						.then(function(response) {
							propertyVM.allServices = response;
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
				updateCheckedServices: function(service) {
					// Verifica si el servicio está marcado
					if (service.checked) {
						// Agrega el servicio a servicesChecked si no está presente
						if (!propertyVM.servicesChecked.some(function(checkedService) {
							console.log("Añade servicio: ", checkedService.name, service.name);
							return checkedService.name === service.name;
						})) {
							console.log("Hace push de servicio: ", service.name);
							propertyVM.servicesChecked.push(service);
						}
					} else {
						// Elimina el servicio de servicesChecked si está presente
						var index = propertyVM.servicesChecked.findIndex(function(checkedService) {
							console.log("Elimina servicio: ", checkedService.name, service.name);
							return checkedService.name === service.name;
						});
						if (index !== -1) {
							console.log("Hace splice de servicio: ", service.name);
							propertyVM.servicesChecked.splice(index, 1);
						}
					}
				},




				getPropertiesBySearch: function(search) {
					propertiesFactory.getPropertyBySearch(search)
						.then(function(response) {
							propertyVM.property = response;
							console.log("Propiedades encontradas:", propertyVM.property);
							propertyVM.size = propertyVM.property.length;
							console.log("Número de propiedades encontradas:", propertyVM.size);
							$location.path('/search/' + propertyVM.search);
						})
						.catch(function(error) {
							console.log("Error al obtener las propiedades:", error);
						});
				},

				updateProperty: function() {
					propertiesFactory.putProperty(propertyVM.property)
						.then(function(response) {
							console.log("Updating property, " + response);

							servicesFactory.putServices(propertyVM.property.id, propertyVM.servicesChecked)
								.then(function(response) {
									console.log("Updating services, " + response);
								});
						});
				},

				createProperty: function() {
					propertiesFactory.postProperty(propertyVM.property)
						.then(function(response) {
							console.log("Creating property, " + response);
							var newPropertyId = response.id;

							servicesFactory.postServices(newPropertyId, propertyVM.servicesChecked)
								.then(function(response) {
									console.log("Creating services, " + response);
								});

						})
						.catch(function(error) {
							console.error("Error al obtener todos los servicios:", error);
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
						propertyVM.functions.createProperty();
						$location.path('/myProperties');
					} else if (propertyVM.functions.where('/editProperty/' + propertyVM.property.id)) {
						propertyVM.functions.updateProperty();
						$location.path('/myProperties');
					} else if (propertyVM.functions.where('/properties/' + propertyVM.search)) {
						propertyVM.functions.getPropertiesBySearch(propertyVM.search);
					} else if (propertyVM.functions.where('/deleteProperty/' + propertyVM.property.id)) {
						propertyVM.functions.deleteProperty(propertyVM.property.id);
						$location.path('/myProperties');
					}
				},

			}


			propertyVM.search = $routeParams.Search;
			if (propertyVM.functions.where('/editProperty/' + $routeParams.ID)) {
				propertyVM.functions.getProperty($routeParams.ID);
			}
			else if (propertyVM.functions.where('/deleteProperty/' + $routeParams.ID)) {
				propertyVM.functions.getProperty($routeParams.ID);
			}
			else if (propertyVM.functions.where('/createProperty')) {
				propertyVM.functions.getAllServices();
			}
			else if (propertyVM.functions.where('/search/' + propertyVM.search)) {
				propertyVM.functions.getPropertiesBySearch(propertyVM.search);
			} else if (propertyVM.functions.where('/myProperties')) {
				propertyVM.functions.propertyHandlerMethod();
			} else if (propertyVM.functions.where('/propertyDetails/' + $routeParams.ID)) {
				propertyVM.functions.getProperty($routeParams.ID);
			}

		}]);