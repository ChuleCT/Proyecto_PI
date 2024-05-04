angular.module('bookingApp')
	.factory("reviewsFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/reviews/';
		var reviewsInterface = {

			// get reviews from a property
			getReviews: function(id) {
				return $http.get(url + id)
					.then(function(response) {
						return response.data;
					});
			},

            // get the review of the session's user for a property
			getReview: function(id) {
				return $http.get(url +"myReview/"+ id)
					.then(function(response) {
						return response.data;
					});
			},

			postReview: function(review, id) {
				return $http.post(url + id, review)
					.then(function(response) {
						return response.status;
					});
			},

			putReview: function(review, id) {
				return $http.put(url + id, review)
					.then(function(response) {
						return response.status;
					});
			},

			deleteReview: function(id) {
				return $http.delete(url + id)
					.then(function(response) {
						return response.status;
					});
			}
		}
		return reviewsInterface;
	}])