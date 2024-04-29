angular.module('bookingApp')
	.controller('accommodationCtrl', ['accommodationsFactory', '$routeParams', '$location',
		function(accommodationsFactory, $routeParams, $location) {

			var accommodationVM = this;
			accommodationVM.accommodations = {};
			accommodationVM.propertyId = undefined;
			accommodationVM.functions = {

				where: function(route) {
					return $location.path() == route;
				},

				getAccommodationsByProperty: function(id) {
					accommodationsFactory.getAccommodations(id)
						.then(function(response) {
							accommodationVM.accommodations = response;
							console.log(accommodationVM.accommodations);
						});

				},
				
				getAccommodation: function(id) {
					accommodationsFactory.getAccommodation(id)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},
				
				updateAccommodation: function(accommodation) {
					accommodationsFactory.putAccommodation(accommodation)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},
				
				createAccommodation: function(accommodation) {
					accommodationsFactory.postAccommodation(accommodation)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				},
				
				deleteAccommodation: function(id) {
					accommodationsFactory.deleteAccommodation(id)
						.then(function(response) {
							accommodationVM.accommodation = response;
						});
				}
			}

			if (accommodationVM.functions.where('/myAccommodations/' + $routeParams.ID)) {
				accommodationVM.propertyId = $routeParams.ID;
				accommodationVM.functions.getAccommodationsByProperty(accommodationVM.propertyId);
			}

		}]);

