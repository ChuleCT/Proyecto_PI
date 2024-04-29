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
			.when("/myAccommodations", {
				controller: "accommodationCtrl",
				controllerAs: "accommodationVM",
				templateUrl: "AccommodationsListTemplate.html",
			})
			.when("/createAccommodation", {
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
	});
