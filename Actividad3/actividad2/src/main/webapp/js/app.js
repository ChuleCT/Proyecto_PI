angular.module('bookingApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "searchCtrl",
    		controllerAs: "searchVM",
    		templateUrl: "SearchTemplate.html",
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
    	})
    	.when("/editUser", {
    		
    		controller: "userCtrl",
    		controllerAs: "userVM",
    		templateUrl: "UserTemplate.html",
    	})
    	//deleteUser by ID
		.when("/deleteUser/:id", {
			controller: "userCtrl",
			controllerAs: "userVM",
			templateUrl: "UserTemplate.html",
			})
});
