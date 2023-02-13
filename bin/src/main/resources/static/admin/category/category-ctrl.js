app.controller("category-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.form = {};
	$scope.initialize = function(){
		// load categories
		$http.get("/rest/category").then(resp => {
			$scope.items = resp.data;
		});
	}
	
	// Hiển thị lên form
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(1)").tab('show')
	}
	
	$scope.next = function(){
		$(".nav-pills a:eq(1)").tab('show')
	}
	
	//Xóa trống form
	$scope.reset = function(){
		$scope.form = {};
	}
	
	$scope.findId = function(){
		var element = document.querySelector('#id');
		$http.get(`/rest/category/${element.value}`).then(resp => {
			if(resp.data != null){
				alert("Thêm loại sản phẩm thất bại trùng id");
			}
		})
	}
	
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/category`, item).then(resp => {
			
			$scope.items.push(resp.data);
			$scope.initialize();
			$scope.reset();
			alert("Thêm loại sản phẩm thành công");
		}).catch(erorr => {
			alert("Thêm loại sản phẩm thất bại");
			console.log("Erorr: ",erorr);
		});
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/category/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(c => c.id == item.id);
			$scope.items[index] = item;
			$scope.reset();
			alert("Cập nhật loại sản phẩm thành công");
		}).catch(erorr => {
			alert("Cập nhật loại sản phẩm thất bại");
			console.log("Erorr: ",erorr);
		});
	}
	
	$scope.delete = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/category/delete/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(c => c.id == item.id)
			$scope.items[index] = item;
			$scope.initialize();
			$scope.reset();
			alert("Xóa loại sản phẩm thành công");
		}).catch(erorr => {
			alert("Xóa loại sản phẩm thất bại");
			console.log("Erorr: ",erorr);
		});
	}
	
	// đổi trạng thái
	$scope.delete2 = function(item){
		$scope.form = angular.copy(item);
		this.delete();
	}
	
	$scope.pager = {	
		page: 0,
		size: 10,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1 * $scope.items.length / this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1; 
		}
	}
	
	
	$scope.initialize();
	$scope.reset();
});