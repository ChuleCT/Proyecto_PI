angular.module('bookingApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "searchCtrl",
    		controllerAs: "searchVM",
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
			controller: "propertiesCtrl",
			controllerAs: "propertyVM",
			templateUrl: "SearchTemplate.html",
		})
		.when("/myProperties", {
			controller: "propertiesCtrl",
			controllerAs: "propertyVM",
			templateUrl: "PropertyTemplate.html",
		})
		.when("/createProperty", {
			controller: "propertiesCtrl",
			controllerAs: "propertyVM",
			templateUrl: "PropertyTemplate.html",
		})
		.when("/editProperty/:ID", {
			controller: "propertiesCtrl",
			controllerAs: "propertyVM",
			templateUrl: "PropertyTemplate.html",
		})
		.when("/deleteProperty/:ID", {
			controller: "propertiesCtrl",
			controllerAs: "propertyVM",
			templateUrl: "PropertyTemplate.html",
		})
});
