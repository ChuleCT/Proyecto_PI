angular.module('bookingApp')
	.controller('propertyCtrl', ['usersFactory', 'propertiesFactory', 'servicesFactory', 'reviewsFactory', 'favoritesFactory', 'accommodationsFactory', 'bookingsFactory', '$routeParams', '$location', '$window',
		function(usersFactory, propertiesFactory, servicesFactory, reviewsFactory, favoritesFactory, accommodationsFactory, bookingsFactory, $routeParams, $location, $window) {

			var propertyVM = this;
			propertyVM.user = {}; // en el propertyDetails se usa para mostrar el nombre del usuario de las reviews y controlar si es el dueño de la propiedad
			propertyVM.property = {};
			propertyVM.search = "";
			propertyVM.servicesChecked = [];
			propertyVM.allServices = [];
			propertyVM.myOpinion = undefined;
			propertyVM.yaValorada = false;
			propertyVM.otherOpinions = undefined;
			propertyVM.userFavorites = [];
			propertyVM.accommodations = [];
			propertyVM.accommodationsSelected = {};
			propertyVM.functions = {

				where: function(route) {
					return $location.path() == route;
				},

				getUser: function() {
					usersFactory.getUser()
						.then(function(response) {
							propertyVM.user = response;
							console.log("User: ", propertyVM.user);
						});
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
									//console.log("getServicesByPropertyId: ", propertyVM.servicesChecked);


									servicesFactory.getAllServices()
										.then(function(response) {
											propertyVM.allServices = response;
											//console.log("All Services: ", propertyVM.allServices);

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
					//console.log("Services Checked: ", propertyVM.servicesChecked);

					propertyVM.allServices.forEach(function(service) {
						var isChecked = propertyVM.servicesChecked.some(function(checkedService) {
							//console.log("Comparing:", service.name, checkedService.name);
							return checkedService.name === service.name;
						});
						service.checked = isChecked;
						//console.log("Service:", service.name, " Checked:", service.checked);
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
							//console.log("Añade servicio: ", checkedService.name, service.name);
							return checkedService.name === service.name;
						})) {
							//console.log("Hace push de servicio: ", service.name);
							propertyVM.servicesChecked.push(service);
						}
					} else {
						// Elimina el servicio de servicesChecked si está presente
						var index = propertyVM.servicesChecked.findIndex(function(checkedService) {
							//console.log("Elimina servicio: ", checkedService.name, service.name);
							return checkedService.name === service.name;
						});
						if (index !== -1) {
							//console.log("Hace splice de servicio: ", service.name);
							propertyVM.servicesChecked.splice(index, 1);
						}
					}
				},




				getPropertiesBySearch: function(search) {
					propertiesFactory.getPropertyBySearch(search)
						.then(function(response) {
							propertyVM.property = response;
							//console.log("Propiedades encontradas:", propertyVM.property);
							propertyVM.size = propertyVM.property.length;
							//console.log("Número de propiedades encontradas:", propertyVM.size);

							propertyVM.functions.getFavorites();
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
									//console.log("Updating services, " + response);
								});
						});
				},

				createProperty: function() {
					propertiesFactory.postProperty(propertyVM.property)
						.then(function(response) {
							//console.log("Creating property, " + response);
							var newPropertyId = response.id;

							servicesFactory.postServices(newPropertyId, propertyVM.servicesChecked)
								.then(function(response) {
									//console.log("Creating services, " + response);
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

				//Metodos para las reviews 

				getMyOpinion: function(propertyId) {
					reviewsFactory.getReview(propertyId)
						.then(function(response) {
							propertyVM.myOpinion = response;
							console.log("Mi opinión: ", propertyVM.myOpinion);
							if (propertyVM.myOpinion.review != undefined) {
								propertyVM.yaValorada = true;
							} else {
								propertyVM.myOpinion = {
									"review": "",
									"grade": undefined
								};
							}
							console.log("Ya valorada: ", propertyVM.yaValorada);
						});
				},

				getOtherOpinions: function(propertyId) {
					reviewsFactory.getReviews(propertyId)
						.then(function(response) {
							propertyVM.otherOpinions = response;
							console.log("Opiniones de otros usuarios: ", propertyVM.otherOpinions);
						});
				},

				createOpinion: function(opinion, propertyId) {
					reviewsFactory.postReview(opinion, propertyId)
						.then(function(response) {
							console.log("Creating opinion, " + response);
						});
				},

				updateOpinion: function(opinion, propertyId) {
					reviewsFactory.putReview(opinion, propertyId)
						.then(function(response) {
							console.log("Updating opinion, " + response);
						});
				},

				deleteOpinion: function() {
					reviewsFactory.deleteReview(propertyVM.property.id)
						.then(function(response) {
							console.log("Deleting opinion, " + response);
							$window.location.reload();
						});
				},

				getFavorites: function() {
					favoritesFactory.getFavorites()
						.then(function(response) {
							propertyVM.userFavorites = response;
							console.log("Favoritos: ", propertyVM.userFavorites);

							propertyVM.functions.initializeFavorites();
						});
				},

				// initializeFavorites pero ahora el userFavorites es una lista con un solo valor Long, que es el id de la propiedad
				initializeFavorites: function() {
					propertyVM.property.forEach(function(property) {
						property.isFavorite = propertyVM.userFavorites.some(function(favorite) {
							return favorite === property.id;
						});
						property.favorite = property.isFavorite;
					});
				},


				toggleFavorite: function(propertyId) {
					var index = propertyVM.userFavorites.indexOf(propertyId);
					if (index === -1) {
						// Si no está en los favoritos, agrégalo
						propertyVM.userFavorites.push(propertyId);
						favoritesFactory.postFavorite(propertyId)
							.then(function(response) {
								console.log("Property added to favorites: ", response);
							})
							.catch(function(error) {
								console.error("Error adding property to favorites: ", error);
							});
					} else {
						// Si está en los favoritos, quítalo
						propertyVM.userFavorites.splice(index, 1);
						favoritesFactory.deleteFavorite(propertyId)
							.then(function(response) {
								console.log("Property removed from favorites: ", response);
							})
							.catch(function(error) {
								console.error("Error removing property from favorites: ", error);
							});
					}
				},

				deleteFavorite: function(propertyId) {
					var index = propertyVM.userFavorites.indexOf(propertyId);
					if (index !== -1) {
						// Si está en los favoritos, quítalo
						propertyVM.userFavorites.splice(index, 1);
						favoritesFactory.deleteFavorite(propertyId)
							.then(function(response) {
								console.log("Property removed from favorites: ", response);
								$window.location.reload();
							})
							.catch(function(error) {
								console.error("Error removing property from favorites: ", error);
							});
					}
				},

				getFavoritesProperties: function() {
					favoritesFactory.getFavorites()
						.then(function(response) {
							propertyVM.userFavorites = response;
							console.log("Favoritos: ", propertyVM.userFavorites);


							propertyVM.property = [];
							angular.forEach(propertyVM.userFavorites, function(property) {
								console.log("Property del inicio del foreach: ", property);

								propertiesFactory.getProperty(property)
									.then(function(response) {
										propertyVM.property.push(response);
										console.log("Property: ", propertyVM.property);
									});

							});
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

				//Metodos para las reviews
				reviewHandlerMethod: function() {
					if (propertyVM.yaValorada) {
						propertyVM.functions.updateOpinion(propertyVM.myOpinion, propertyVM.property.id);
						//Para que se recargue la pagina y se vea la review actualizada recargamos la pagina
					} else {
						propertyVM.functions.createOpinion(propertyVM.myOpinion, propertyVM.property.id);
						//Para que se recargue la pagina y se vea la review actualizada recargamos la pagina
					}
					$window.location.reload();
				},

				//Metodo para gestionar las reservas de habitaciones
				getAccommodations: function(propertyId) {
					accommodationsFactory.getAccommodations(propertyId)
						.then(function(response) {
							propertyVM.accommodations = response;
							console.log("Alojamientos: ", propertyVM.accommodations);
						});
				},

				crearReservaProvisional: function() {
					console.log("Reserva provisional: ", propertyVM.accommodationsSelected);
					for (var clave in propertyVM.accommodationsSelected) {
						bookingsFactory.postProvisionalBooking(clave, propertyVM.accommodationsSelected[clave])
					}
					$location.path('/shoppingCart');
				},

				orderByRating: function() {
					console.log("Propiedades sin ordenar: ", propertyVM.property);
					propertyVM.property.sort(function(a, b) {
						return b.gradesAverage - a.gradesAverage;
					});
					console.log("Ordenadas por rating: ", propertyVM.property);

				},

				propertiesByAvailability: function(availability) {
					propertiesAux = [];
					propertiesFactory.getPropertyBySearch(propertyVM.search)
						.then(function(response) {
							propertyVM.functions.getFavorites();
							propertyVM.property = response;
							propertyVM.size = propertyVM.property.length;

							switch (availability) {
								case 0:
									// Sin disponibilidad 
									for (let i = 0; i < propertyVM.property.length; i++) {
										if (propertyVM.property[i].available == 0) {
											propertiesAux.push(propertyVM.property[i]);
										}
									}
									break;
								case 1:
									// Con disponibilidad
									for (let i = 0; i < propertyVM.property.length; i++) {
										if (propertyVM.property[i].available == 1) {
											propertiesAux.push(propertyVM.property[i]);
										}
									}
									break;
								case 2:
									// Todas
									propertiesAux = propertyVM.property;
									break;
								default:
									console.log("Error en la selección de disponibilidad");
									break;
							}
							propertyVM.property = propertiesAux;
							console.log("Propiedades filtradas por disponibilidad: ", propertyVM.property);
						})
						.catch(function(error) {
							console.log("Error al obtener las propiedades:", error);
						});
				},

			},






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
				// Obtener usuario
				propertyVM.functions.getUser();
				//Obtengo las habitaciones de la propiedad
				propertyVM.functions.getAccommodations($routeParams.ID);
				// Obtener opiniones de la propiedad seleccionada
				propertyVM.functions.getMyOpinion($routeParams.ID);
				propertyVM.functions.getOtherOpinions($routeParams.ID);

			} else if (propertyVM.functions.where('/favoritesProperties')) {
				propertyVM.functions.getFavoritesProperties();
				console.log("Property en el else if este : ", propertyVM.property);

			}


		}]);