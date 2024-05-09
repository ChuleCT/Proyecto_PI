angular.module('bookingApp')
	.controller('headerCtrl', ['usersFactory', '$window', function(usersFactory, $window) {
		var headerViewModel = this;
		headerViewModel.user = {};
		headerViewModel.functions = {
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
				window.location.pathname = '/actividad2/LogoutServlet.do';
			}
		}
		headerViewModel.functions.readUser();
	}])