angular.module('bookingApp')
	.factory('usersFactory', ['$http', function ($http) {

		//TODO Add the base URL needed for executing the web services
		var url = 'https://localhost:8443/actividad2/rest/users/';
		var usersInterface = {
			getUser: function () {

				url = url;
				return $http.get(url)
					.then(function (response) {
						return response.data;
					});


			},
			putUser: function (user) {
				url = url;
				return $http.put(url, user)
					.then(function (response) {
						return response.status;
					});
			},
			deleteUser: function (user) {
				url = url;
				return $http.delete(url, user)
					.then(function (response) {
						return response.status;
					});
			}
		}
		return usersInterface;
	}])