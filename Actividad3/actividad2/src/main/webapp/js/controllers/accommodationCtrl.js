angular.module('bookingApp')
	.controller('accommodationCtrl', ['accommodationsFactory', 'usersFactory', 'propertiesFactory','$routeParams', '$location',
		function(accommodationsFactory, usersFactory, propertiesFactory, $routeParams, $location) {

			var accommodationVM = this;
			accommodationVM.accommodations = {};
			accommodationVM.user = {};
			accommodationVM.property = {};
			accommodationVM.propertyID = undefined;
			accommodationVM.functions = {

				where: function(route) {
					return $location.path() == route;
				},
				
				getUser: function() {
					usersFactory.getUser()
						.then(function(response) {
							accommodationVM.user = response;
							
							if (accommodationVM.property.idu != accommodationVM.user.id) {
								$location.path('/noPermission');
							}
							console.log(accommodationVM.user);
						});
				},
				
				getProperty: function(id) {
					propertiesFactory.getProperty(id)
						.then(function(response) {
							accommodationVM.property = response;
							accommodationVM.functions.getUser();
							console.log(accommodationVM.property);
						});
				},

				getAccommodationsByProperty: function(id) {
					accommodationsFactory.getAccommodations(id)
						.then(function(response) {
							accommodationVM.functions.getProperty(id);
							accommodationVM.accommodations = response;
							console.log(accommodationVM.accommodations);
						});

				},

				getAccommodation: function(id) {
					accommodationsFactory.getAccommodation(id)
						.then(function(response) {
							accommodationVM.accommodations = response;
							
							//Cojo una de las accommodations para obtener el id de la propiedad
							accommodationVM.propertyID = accommodationVM.accommodations.idp;
							accommodationVM.functions.getProperty(accommodationVM.propertyID);
							
							console.log(accommodationVM.accommodations);
						});
				},

				getAccommodationVacia: function(id) {
					accommodationsFactory.getAccommodationVacia(id)
						.then(function(response) {
							accommodationVM.accommodations = response;
							console.log(accommodationVM.accommodations);
						});
				},

				updateAccommodation: function(accommodation) {
					accommodationsFactory.putAccommodation(accommodation)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},

				createAccommodation: function(accommodation) {
					console.log("Habitacion:", accommodation);
					accommodationsFactory.postAccommodation(accommodation, $routeParams.ID)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},

				deleteAccommodation: function(id) {
					accommodationsFactory.deleteAccommodation(id)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},

				accommodationHandlerMethod: function() {
					console.log("He entrado en el accommodationHandlerMethod");
					if (accommodationVM.functions.where('/editAccommodation/' + $routeParams.ID)) {
						console.log($location.path());
						accommodationVM.functions.updateAccommodation(accommodationVM.accommodations);
						$location.path('/myProperties');
					} else if (accommodationVM.functions.where('/createAccommodation/' + $routeParams.ID)) {
						console.log($location.path());
						accommodationVM.functions.createAccommodation(accommodationVM.accommodations);
						$location.path('/myProperties');
					} else if (accommodationVM.functions.where('/deleteAccommodation/' + $routeParams.ID)) {
						console.log($location.path());
						accommodationVM.functions.deleteAccommodation(accommodationVM.accommodations.id);
						$location.path('/myProperties');
					}
				}
			}

			if (accommodationVM.functions.where('/myAccommodations/' + $routeParams.ID)) {
				accommodationVM.propertyID = $routeParams.ID;
				accommodationVM.functions.getAccommodationsByProperty($routeParams.ID);
			} else if (accommodationVM.functions.where('/editAccommodation/' + $routeParams.ID) || accommodationVM.functions.where('/deleteAccommodation/' + $routeParams.ID)) {
				console.log("He entrado en el if");
				accommodationVM.functions.getAccommodation($routeParams.ID);
			} else if (accommodationVM.functions.where('/createAccommodation/' + $routeParams.ID)) {
				accommodationVM.propertyID = $routeParams.ID;
				console.log("He entrado en el if");
				accommodationVM.functions.getProperty($routeParams.ID);
			}
		}]);

