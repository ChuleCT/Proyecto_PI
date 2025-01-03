angular.module('bookingApp', ['ngRoute'])
	.config(function($routeProvider) {
		$routeProvider
			.when("/", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "WelcomeTemplate.html",
				resolve: {
					// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
					//does any requested update before reading the orders.
					// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
					delay: function($q, $timeout) {
						var delay = $q.defer();
						$timeout(delay.resolve, 500);
						return delay.promise;
					}
				}
			}).when("/createUser", {
				controller: "userCtrl",
				controllerAs: "userVM",
				templateUrl: "UserTemplate.html",
			})
			.when("/editUser", {

				controller: "userCtrl",
				controllerAs: "userVM",
				templateUrl: "UserTemplate.html",
			})
			.when("/deleteUser", {
				controller: "userCtrl",
				controllerAs: "userVM",
				templateUrl: "UserTemplate.html",
			})
			.when("/search/:Search", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "SearchTemplate.html",
				
			})
			.when("/myProperties", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "PropertiesListTemplate.html",
				resolve: {
					delay: function($q, $timeout) {
						var delay = $q.defer();
						$timeout(delay.resolve, 500);
						return delay.promise;
					}
				},
			})
			.when("/createProperty", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "PropertyTemplate.html",
			})
			.when("/editProperty/:ID", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "PropertyTemplate.html",
			})
			.when("/deleteProperty/:ID", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "PropertyTemplate.html",
			})
			.when("/myAccommodations/:ID", {
				controller: "accommodationCtrl",
				controllerAs: "accommodationVM",
				templateUrl: "AccommodationsListTemplate.html",
			})
			.when("/createAccommodation/:ID", {
				controller: "accommodationCtrl",
				controllerAs: "accommodationVM",
				templateUrl: "AccommodationTemplate.html",
			})
			.when("/editAccommodation/:ID", {
				controller: "accommodationCtrl",
				controllerAs: "accommodationVM",
				templateUrl: "AccommodationTemplate.html",
			})
			.when("/deleteAccommodation/:ID", {
				controller: "accommodationCtrl",
				controllerAs: "accommodationVM",
				templateUrl: "AccommodationTemplate.html",
			})
			.when("/myBookings", {
				controller: "bookingCtrl",
				controllerAs: "bookingVM",
				templateUrl: "BookingsListTemplate.html",
				resolve: {
					delay: function($q, $timeout) {
						var delay = $q.defer();
						$timeout(delay.resolve, 500);
						return delay.promise;
					}
				},
			})
			.when("/propertyDetails/:ID", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "PropertyDetailsTemplate.html",
			})
			.when("/favoritesProperties", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "FavoritesListTemplate.html",
			})
			.when("/shoppingCart", {
				controller: "bookingCtrl",
				controllerAs: "bookingVM",
				templateUrl: "ShoppingCartTemplate.html",
				resolve: {
					delay: function($q, $timeout) {
						var delay = $q.defer();
						$timeout(delay.resolve, 500);
						return delay.promise;
					}
				},
			})
			.when("/noPermission", {
				controller: "propertyCtrl",
				controllerAs: "propertyVM",
				templateUrl: "NoPermissionTemplate.html",
			})
	});
