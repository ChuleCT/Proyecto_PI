angular.module('bookingApp')
	.controller('headerCtrl', ['usersFactory', function(usersFactory) {
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
			}
		}
		headerViewModel.functions.readUser();
	}])