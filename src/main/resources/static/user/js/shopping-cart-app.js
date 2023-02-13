/**
 * 
*/

const rootUrl = "http://localhost:8080";
const app = angular.module("shopping-cart-app", []);

//controller
app.controller("shopping-cart-ctrl", function($scope, $http) {
	$scope.shop = {
		items: [],
		loadFromRestApi() {
			$http.get('http://localhost:8080/rest/product').then(resp => {
				let db = resp.data;
				db.map(o => {
					$http.get('http://localhost:8080/rest/pricehistory/' + o.id).then(resp =>
						o.price = resp.data.price);
				});
				this.items = db;
			});
		}
	}

	$scope.productDetail = {
		quantity: 1,
		checkQuantity(id) {
			let inputQty = document.querySelector('#input-qty');
			$http.get(rootUrl + "/rest/product/" + id).then(response => {
				let qty = inputQty.value;
				let product = response.data;
				if (qty > product.quantity) {
					inputQty.value = product.quantity;
				}
			});
		},
		add(id) {
			let inputQty = document.querySelector('#input-qty');
			$scope.cart.add(id, inputQty.value);
		}
	}

	//Load current User
	$scope.currentUser = {};
	$scope.loadCurrentUser = async function() {
		await $http.get(rootUrl + "/rest/user/current").then(response => {
			$scope.currentUser = response.data;
		}).catch(err => {
			console.log(err);
		});
	}



	//load current cart
	$scope.currentCart = async function() {
		let cart = await $http.get(rootUrl + "/rest/cart/user/" + $scope.currentUser.id).then(response => {
			return response.data;
		}).catch(err => {
			console.log(err);
			return null;
		});
		return cart;
	}



	//cart methods
	$scope.cart = {
		//cart items
		items: [],

		//add method
		async add(id, quantity) {
			//if does not login
			await $scope.loadCurrentUser();
			let user = $scope.currentUser;
			if (!user) {
				window.location.href = "/access/login";
			} else {
				let exist = await this.items.find(o => {
					if (o.product.id == id) {
						return o;
					}
				});
				console.log('asdfasdfasdfasfasdfasdfasdf')
				console.log(exist);
				if (exist) {
					exist.quantity = Number(exist.quantity) + Number(quantity);
					if (exist.quantity > exist.product.quantity) {
						exist.quantity = exist.product.quantity;
					}
					$http.put('http://localhost:8080/rest/cart/' + exist.id, exist.quantity).then(response => {
						console.log(response.data);
						this.loadFromRestApi();
						alert('Đã cập nhật sản phẩm vào giỏ hàng!');
					});
				} else {
					console.log('in asdfasdfasdf')
					let product = await $http.get(rootUrl + "/rest/product/" + id).then(response => {
						return response.data;
					}).catch(err => {
						console.log(err)
						return null;
					});
					console.log('this is product')
					console.log(product)
					let entity = {
						id: null,
						cart: $scope.currentCart,
						product: product,
						creatDate: null,
						quantity: quantity
					}
					$http.post('http://localhost:8080/rest/cart', entity).then(response => {
						console.log(response.data);
						this.loadFromRestApi();
						alert('Đã thêm sản phẩm vào giỏ hàng!');
					}).catch(err => {
						console.log(err);
					});
				}
				this.amount();
			}
			this.loadFromRestApi();
		},

		//update method
		async update(id) {
			let item = this.items.find(item => item.id == id);
			let inputQuantity = document.querySelector('#input-quantity-' + id);
			let quantity = inputQuantity.value;
			if (quantity == null) {
				quantity = item.quantity;
			} else if (isNaN(quantity)) {
				quantity = item.quantity;
			} else if (Number(quantity < 1)) {
				quantity = item.quantity;
			}
			await $http.get('http://localhost:8080/rest/product/' + item.product.id).then(response => {
				let product = response.data;
				if (Number(quantity) > product.quantity) {
					quantity = product.quantity;
				}
			});

			await $http.put('http://localhost:8080/rest/cart/' + id, Number(quantity)).then(response => {
				let cartItem = response.data;
				inputQuantity.value = cartItem.quantity;
			});
			this.amount();
		},

		//remove method
		remove(id) {
			$http.delete('http://localhost:8080/rest/cart/' + id).then(response => {
				document.querySelector('#item-r-' + id).remove();
			});
			this.amount();
		},

		//clear all items
		clear() {
			$http.delete('http://localhost:8080/rest/cart/clear').then(reponse => {
				document.querySelector("#list-item").innerHTML = "";
			});
			this.amount();
		},

		//Total cart
		amount() {
			$http.get('http://localhost:8080/rest/cart/total').then(response => {
				let totalHolder = document.querySelector("#order-total");
				if (totalHolder) {
					totalHolder.textContent = response.data;
				}
			});
		},
		//load from rest api
		async loadFromRestApi() {
			let a = await $http.get('http://localhost:8080/rest/cart/items').then(resp => {
				return resp.data;
			}).then(datas => {
				datas.map(o => {
					$http.get('http://localhost:8080/rest/pricehistory/' + o.product.id).then(resp => {
						o.price = resp.data.price
					});
					$http.get('http://localhost:8080/rest/cart/cost/' + o.id).then(resp => {
						o.cost = resp.data
					});
				});
				return datas;
			}).then(afterHandled => {
				this.items = afterHandled;
				this.amount();
			});
		}
	};
	$scope.loadCurrentUser();
	$scope.cart.loadFromRestApi();
	$scope.shop.loadFromRestApi();

	//run 
});

