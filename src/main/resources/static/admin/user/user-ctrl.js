app.controller('user-ctrl', function($scope, $http) {
	//Contain curent user	
	$scope.currentUser = {};
	//Contain direct users
	$scope.directUsers = [];

	$scope.users = [];

	$scope.form = null;

	//Get current user
	$scope.getCurrentUser = async function() {
		await $http.get(rootUrl + "/rest/user/current").then(response => {
			$scope.currentUser = response.data;
		});
	};

	//Get user by role id
	$scope.getUserByRoleId = async function(roleId) {
		let users = [];
		await $http.get(rootUrl + "/rest/authority/role/" + roleId).then(response => {
			users = response.data;
		});
		return users;
	};

	//Get direct user 
	$scope.getDirectUsers = async function() {
		$scope.directUsers = await this.getUserByRoleId('DIRECT');
	};




	$scope.init = function() {
		//Run get direct users
		$scope.getDirectUsers();

		//Run get currentuser
		$scope.getCurrentUser();
	}

	$scope.toggleClass = function(selector, clazz) {
		let el = document.querySelector(selector);
		el.classList.toggle(clazz);
	}


	//User methods
	$scope.user = {
		loadFromRestApi() {
			$http.get(rootUrl + "/rest/user").then(response => {
				let db = response.data;
				$scope.users = db.filter(item => {
					return (item.id !== $scope.currentUser.id);
				});
				$scope.users = $scope.userss.filter(item => {
					let dus = $scope.directUsers.map(du => {
						return du.user;
					});
					let exist = dus.find(du => {
						if (du.id == item.id) {
							return du;
						}
					});
					return exist == null;
				})
				/*$scope.userss.map(item => {
					return item.registerDate = new Date(item.registerDate);
				})*/
			});
		},
		search(keyword) {
			if (keyword.trim().length == 0) {
				this.loadFromRestApi();
			} else {
				$http.get(rootUrl + "/rest/user/search/" + keyword).then(response => {
					let db = response.data;
					$scope.users = db.filter(item => {
						return (item.id !== $scope.currentUser.id);
					});
					$scope.users = $scope.userss.filter(item => {
						let dus = $scope.directUsers.map(du => {
							return du.user;
						});
						let exist = dus.find(du => {
							if (du.id == item.id) {
								return du;
							}
						});
						return exist == null;
					})
					/*$scope.userss.map(item => {
						return item.registerDate = new Date(item.registerDate);
					})*/
				});
			}
		},
		edit(user) {
			$scope.form = angular.copy(user);
			$(".nav-pills a:eq(1)").tab('show')
		},
		update() {
			let data = angular.copy($scope.form);
			$http.put(rootUrl + "/rest/user/" + data.id, data).then(resonse => {
				console.log(resonse.data);
				alert('Cập nhật thành công');
				this.loadFromRestApi();
				alert('Cập nhật thành công!');
			}).catch(err => {
				console.log(err);
			})
		},
		delete(id) {
			let item = $scope.users.find(u => {
				if (u.id == id) {
					return u;
				}
			});
			item.activated = false;
			$http.put(rootUrl + "/rest/user/" + item.id, item).then(response => {
				this.loadFromRestApi();
			}).catch(err => {
				alert('Vô hiệu hóa thất bại')
				console.log('Vô hiệu hóa thất bại');
				console.log(err)
			});
		}
		,
		imageChanged(files) {
			let data = new FormData();
			data.append('file', files[0]);
			$http.post(rootUrl + '/rest/upload/user', data, {
				transformRequest: angular.identity,
				headers: {
					'Content-Type': undefined
				}
			}).then(response => {
				let path = response.data;
				let filename = path.substring(path.lastIndexOf('\\') + 1);
				$scope.form.avatar = filename;
			}).catch(error => {
				alert('Lỗi upload');
				console.log(error);
			});
		}
	}

	$scope.checkFullname = function() {
		if ($scope.form.fullname.length == 0) {
			alert('Vui lòng nhập họ tên');
		}
	}
	$scope.checkEmail = function() {
		if ($scope.form.email.length == 0) {
			alert('Vui lòng nhập email');
		}
	}

	$scope.checkPhoneNumber = function() {
		if ($scope.form.phoneNumber.length == 0) {
			alert('Vui lòng nhập số điện thoại');
		} else {
			if (isNaN($scope.form.phoneNumber)) {
				alert('Số điện thoại sai định dạng');
			} else if ($scope.form.phoneNumber.length != 10) {
				alert('Số điện thoại sai định dạng');
			}
		}
	}


	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.users.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1 * $scope.users.length / this.size);
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

	//Load from database
	$scope.user.loadFromRestApi();
})