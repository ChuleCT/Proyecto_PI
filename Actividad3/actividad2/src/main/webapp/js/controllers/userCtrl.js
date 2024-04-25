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
				if(userVM.functions.where("/editUser")){
					console.log("Edit user, path:" + $location.path());
					userVM.functions.updateUser();
				}
				if(userVM.functions.where("/deleteUser")){
					console.log("Delete user, path:" + $location.path());
					userVM.functions.deleteUser();
				}
				else {
					console.log($location.path());
				}
				$location.path("/");
			}
		}
		userVM.functions.getUser();
	}])