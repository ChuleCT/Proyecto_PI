angular.module('bookingApp')
	.controller('userCtrl', ['usersFactory', '$location', function(usersFactory, $location) {
		var userVM = this;
		userVM.user = {};
		userVM.message = undefined;
		userVM.functions = {
			where: function(route) {
				return $location.path() == route;
			},
			getUser: function() {
				usersFactory.getUser()
					.then(function(response) {
						userVM.user = response;
					});
			},
			createUser: function() {
				usersFactory.postUser(userVM.user)
					.then(function(response) {
						console.log("User created, status: " + response);
						localStorage.setItem("Recarga", 1);
						$location.path("/");
					}, function(error) {
						console.log("Error creating user, status: " + error);
						userVM.message = "Ya existe un usuario con ese email, por favor, elija otro";
					}
					);
			},
			updateUser: function() {
				usersFactory.putUser(userVM.user)
					.then(function(response) {
						console.log("User updated, status: " + response);
						localStorage.setItem("Recarga", 1);
						$location.path("/");
					}, function(error) {
						console.log("Error updating user, status: " + error);
						userVM.message = "Ya existe un usuario con ese email, por favor, elija otro";
					});
			},
			deleteUser: function() {
				usersFactory.deleteUser(userVM.user)
					.then(function(response) {
						console.log("User deleted, status: " + response);
						window.location.pathname = '/actividad2/LogoutServlet.do';
					});
			},
			userHandlerMethod: function() {
				if (userVM.functions.where("/createUser")) {
					console.log("Create user, path:" + $location.path());
					userVM.functions.createUser();
				}
				if (userVM.functions.where("/editUser")) {
					console.log("Edit user, path:" + $location.path());
					userVM.functions.updateUser();
				}
				if (userVM.functions.where("/deleteUser")) {
					console.log("Delete user, path:" + $location.path());
					userVM.functions.deleteUser();
				}
				else {
					console.log($location.path());
				}
			}
		}

		if (userVM.functions.where("/editUser") || userVM.functions.where("/deleteUser")) {
			userVM.functions.getUser();
		} else if (userVM.functions.where("/createUser")) {
			userVM.user = {
				"name": "",
				"surname": "",
				"password": "",
				"email": ""
			};
		}
	}])