angular.module('bookingApp')
	.factory("reviewsFactory", ['$http', function($http) {
		var url = 'https://localhost:8443/actividad2/rest/reviews/';
		var reviewsInterface = {

			// get reviews from a property
			getReviews: function(id) {
				return $http.get(url + id);
			},

            // get a specific review
			getReviews: function(id) {
				return $http.get(url +"2/"+ id);
			},

			postReview: function(review, id) {
				return $http.post(url + id, review);
			},

			putReview: function(review, id) {
				return $http.put(url + id, review);
			},

			deleteReview: function(id) {
				return $http.delete(url + id);
			}
		};
		return reviewsInterface;
	}]);