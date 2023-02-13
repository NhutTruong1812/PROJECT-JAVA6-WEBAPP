app.controller('authority-ctrl', function($scope, $http) {
	$scope.currentUser = {};
	$scope.data = {}
	$scope.authority = {
		async loadFromRestApi() {
			await $http.get(rootUrl + "/rest/authority").then(response => {
				$scope.data.authority = response.data;
			});
			await $http.get(rootUrl + "/rest/user").then(response => {
				let direct = $scope.data.authority.find(item => {
					if (item.role.id == 'DIRECT') {
						return item;
					}
				});
				let users = response.data;
				$scope.data.user = users.filter(function(item) {
					return item.id !== $scope.currentUser.id || item.id !== direct.user.id;
				});
			});
			await $http.get(rootUrl + "/rest/role").then(response => {
				$scope.data.role = response.data;
			});
			console.log($scope.data)
		},
		checkUserRole(userId, roleId) {
			let au = $scope.data.authority.find(item => {
				if (item.user.id == userId && item.role.id == roleId) {
					return item;
				}
			});
			if (au) {
				return true;
			}
			return false;
		},

		async getUserById(userId) {
			let user = {};
			await $http.get(rootUrl + "/rest/user/" + userId).then(response => {
				user = response.data;
			});
			return user;
		},
		async getRoleById(roleId) {
			let role = {};
			await $http.get(rootUrl + "/rest/role/" + roleId).then(response => {
				role = response.data;
			});
			return role;
		},
		async checkBoxAction(event, userId, roleId) {
			let au = $scope.data.authority.find(item => {
				if (item.user.id == userId && item.role.id == roleId) {
					return item;
				}
			});

			if (event.target.checked) {
				if (au == null) {
					let userGet = await this.getUserById(userId);
					let roleGet = await this.getRoleById(roleId);
					let entity = {
						id: null,
						user: userGet,
						role: roleGet
					}
					$http.post(rootUrl + "/rest/authority", entity).then(response => {
						this.authority.loadFromRestApi();
					});
				}
			} else {
				if (au) {
					$http.delete(rootUrl + "/rest/authority/" + au.id).then(response => {
						this.authority.loadFromRestApi();
					});
				}
			}
		},
		getCurrentUser() {
			$http.get(rootUrl + "/rest/user/current").then(response => {
				$scope.currentUser = response.data;
				console.log($scope.currentUser);
			});
		}
	}
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.data.user.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1 * $scope.data.user.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
	$scope.authority.getCurrentUser();
	$scope.authority.loadFromRestApi();
});