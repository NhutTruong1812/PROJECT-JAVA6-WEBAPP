app.controller("order-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.status = [];
	$scope.form = {};
	$scope.initialize = function() {
		// load order
		$http.get("/rest/order").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
			})
		});
		//load status
		$http.get("/rest/status").then(resp => {
			$scope.status = resp.data;
		});
	}

	// Hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(1)").tab('show')
	}

	$scope.next = function() {
		$(".nav-pills a:eq(1)").tab('show')
	}

	//Xóa trống form
	$scope.reset = function() {
		$scope.form = {};
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/order/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(c => c.id == item.id);
			$scope.items[index] = item;
			$scope.reset();
			alert("Cập nhật đơn hàng thành công");
		}).catch(erorr => {
			alert("Cập nhật đơn hàng thất bại");
			console.log("Erorr: ", erorr);
		});
	}

	$scope.delete = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/order/delete/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(c => c.id == item.id)
			$scope.items[index] = item;
			$scope.initialize();
			$scope.reset();
			alert("Xóa đơn hàng thành công");
		}).catch(erorr => {
			alert("Xóa đơn hàng thất bại");
			console.log("Erorr: ", erorr);
		});
	}

	// đổi trạng thái
	$scope.delete2 = function(item) {
		$scope.form = angular.copy(item);
		this.delete();
	}



	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1 * $scope.items.length / this.size);
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

	$scope.initialize();
});