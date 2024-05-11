angular.module('bookingApp')
	.controller('headerCtrl', ['usersFactory', '$window', '$location', function(usersFactory, $window, $location) {
		var headerViewModel = this;
		headerViewModel.user = {};
		headerViewModel.functions = {

			where: function(route) {
				return $location.path() == route;
			},
			
			readUser: function() {
				usersFactory.getUser()
					.then(function(response) {
						headerViewModel.user = response
						console.log(headerViewModel.user.name);
					},
						function(response) {
							console.log('Error: ' + response);
						});
			},
			logout: function() {
				$window.location.pathname = '/actividad2/LogoutServlet.do';
			}
		}
		headerViewModel.functions.readUser();
	}])