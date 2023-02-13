app.controller("product-ctrl", function($scope, $http) {
	//form
	$scope.form = {};

	//item product
	$scope.items = [];

	//form
	$scope.filenames = [];

	//item category
	$scope.itemCates = [];

	$scope.initialize = function() {
		//Load product 
		$http.get("/rest/product/pro").then(resp => {
			let pro = resp.data;
			pro.map(p => {
				p.createDate = new Date(p.createDate);
				$http.get('http://localhost:8080/rest/pricehistory/' + p.id).then(resp => p.price = resp.data);
				$http.get('http://localhost:8080/rest/product/img/' + p.id).then(resp => {
					p.img = resp.data;
				});
			})
			this.items = pro;
			console.log($scope.items);
		})

		//load category
		$http.get("/rest/category/cate").then(resp => {
			$scope.itemCates = resp.data;
			console.log($scope.itemCates);
		})

	}


	//form add new
	$scope.addnew = function() {
		$scope.reset();
		$(".nav-tabs a:eq(1)").tab('show')
	}

	/* PRODUCT */

	//Create
	$scope.create = function() {
		var item = angular.copy($scope.form);

		var product = {
			name: item.name,
			quantity: item.quantity,
			banner: item.banner,
			desciption: item.description,
			category: item.category,
			deleted: item.deleted
		}

		$http.post(`/rest/product`, product).then(resp => {
			var productAdd = resp.data;
			$scope.items.push(productAdd);
			console.log($scope.filenames);
			/**Product img */
			$scope.filenames.map(i => {
				var images = {
					name: i,
					product: productAdd
				};
				console.log(images);
				//Thêm img detail
				$http.post(`/rest/product/imgdetails/`, images).then(resp => {
				}).catch(Error => {
					console.log("KO thêm chi tiết ảnh")
				})

			})

			/**Prices history */
			var pricehistory = {
				product: productAdd,
				price: item.price.price,
				changeDate: new Date()
			};
			console.log(pricehistory);
			$http.post(`/rest/product/p/`, pricehistory).then(resp => {
			}).catch(Error => {
				console.log("Lỗi Thêm Giá")
			})
			$scope.reset();
		}).catch(Error => {
			alert("Thêm Thất Bại")
		})

	}
	//Edit
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		var it = this.form.img;
		var it2 = [];
		console.log(it);
		it.map(p => {
			it2.push(p.name);
		});

		$scope.filenames = it2;
		$(".nav-tabs a:eq(1)").tab('show')

	}

	//update
	$scope.update = function() {

		var item = angular.copy($scope.form);

		var product = {
			id: item.id,
			name: item.name,
			quantity: item.quantity,
			banner: item.banner,
			createDate: new Date(),
			desciption: item.description,
			category: item.category,
			deleted: item.deleted
		}
		console.log(product);

		$http.put(`/rest/product/${product.id}`, product).then(resp => {
			var index = $scope.items.findIndex(p => p.id == product.id);
			var productUpdate = resp.data;
			$scope.items[index] = productUpdate;
			$scope.items.push(productUpdate);

			console.log($scope.filenames);
			//Xóa Image Cũ
			$http.delete(`/rest/product/delete/imgdetails/${product.id}`).then(resp => {
				console.log("Xóa ")
			}).catch(Error => {
				console.log("Không xóa img detaile")
			})
			/**Product img */

			$scope.filenames.map(i => {
				var images = {
					name: i,
					product: productUpdate
				};
				console.log(images);
				//Thêm img detail
				$http.post(`/rest/product/imgdetails/`, images).then(resp => {
				}).catch(Error => {
					console.log("KO thêm chi tiết ảnh")
				})

			})

			/**Prices history */
			var pricehistory = {
				product: productUpdate,
				price: item.price.price,
				changeDate: new Date()
			};
			console.log(pricehistory);
			$http.post(`/rest/product/p/`, pricehistory).then(resp => {
			}).catch(Error => {
				console.log("Lỗi Thêm Giá")
			})
			alert('Cập Nhật Thành Công')
			$scope.reset();
			$(".nav-tabs a:eq(0)").tab('show')
		}).catch(Error => {
			alert("Cập Nhật Thất Bại")
		})
	}
	//Delete
	$scope.delete = function(item) {
		var product = {
			id: item.id,
			name: item.name,
			quantity: item.quantity,
			banner: item.banner,
			createDate: new Date(),
			desciption: item.description,
			category: item.category,
			deleted: true
		}
		console.log(product);

		$http.put(`/rest/product/${product.id}`, product).then(resp => {
			var index = $scope.items.findIndex(p => p.id == product.id);
			var productUpdate = resp.data;
			$scope.items[index] = productUpdate;
			$scope.items.push(productUpdate);

			alert('Vô Hiệu Hóa Thành Công')
			$(".nav-tabs a:eq(0)").tab('show')
			$scope.reset();
		}).catch(Error => {
			alert("Vô Hiệu Hóa Thất Bại")
		})
	}

	//reset
	$scope.reset = function() {
		$scope.initialize();
		$scope.form = {};
		$scope.filenames = [];
	}

	//Image banner
	$scope.posterChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/product', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			let path = resp.data;
			let filename = path.substring(path.lastIndexOf('\\') + 1);
			$scope.form.banner = filename;
		}).catch(error => {
			alert("Lỗi poster")
			console.log("Error: " + error)
		})

	}
	//Image banner details
	$scope.imgChanged = function(files) {
		var data = new FormData();
		for (var i = 0; i < files.length; i++) {
			data.append('file', files[i]);
		}
		$http.post('/rest/upload/img/bannerdetail/', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			console.log("DT: " + resp.data)
			$scope.filenames = resp.data;
		}).catch(error => {
			alert("Lỗi poster")
			console.log("Error: ")
			console.log( error)
		})

	}

	$scope.initialize();

	//Phan trang
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

});