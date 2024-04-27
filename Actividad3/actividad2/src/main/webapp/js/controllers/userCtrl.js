angular.module('bookingApp')
	.controller('userCtrl', ['usersFactory', '$location', function (usersFactory, $location) {
		var userVM = this;
		userVM.user = {};
		userVM.functions = {
			where : function(route){
				return $location.path() == route;
			},
			getUser: function () {
				usersFactory.getUser()
					.then(function (response) {
						userVM.user = response;
					});
			},
			createUser: function() {
				usersFactory.postUser(userVM.user)
					.then(function(response) {
						console.log("User created, status: " + response);
					});
			},
			updateUser: function () {
				usersFactory.putUser(userVM.user)
					.then(function (response) {
						console.log("User updated, status: " + response);
					});
			},
			deleteUser: function () {
				usersFactory.deleteUser(userVM.user)
					.then(function (response) {
						console.log("User deleted, status: " + response);
					});
			},
			userHandlerMethod: function(){
				if (userVM.functions.where("/createUser")) {
					console.log("Create user, path:" + $location.path());
					userVM.functions.createUser();
				}
				if(userVM.functions.where("/editUser")){
					console.log("Edit user, path:" + $location.path());
					userVM.functions.updateUser();
					$location.path("/");
				}
				if(userVM.functions.where("/deleteUser")){
					console.log("Delete user, path:" + $location.path());
					userVM.functions.deleteUser();
					$location.path("/");
				}
				else {
					console.log($location.path());
				}
			}
		}
		userVM.functions.getUser();
	}])