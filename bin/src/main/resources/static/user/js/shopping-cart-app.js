/**
 * 
*/


const app = angular.module("shopping-cart-app", []);

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

	$scope.cart = {
		items: [],
		add(id) {
			let item = this.items.find(item => item.product.id == id);
			if (item) {
				item.quantity++;
				$http.put('http://localhost:8080/rest/cart/' + item.id, item.quantity).then(response => {
					console.log(response.data);
					this.loadFromRestApi();
				});
			} else {
				$http.post('http://localhost:8080/rest/cart', id);
			}
			this.amount();
		},
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
		remove(id) {
			$http.delete('http://localhost:8080/rest/cart/' + id).then(response => {
				document.querySelector('#item-r-' + id).remove();
			});
			this.amount();
		},
		clear() {
			$http.delete('http://localhost:8080/rest/cart/clear').then(reponse => {
				document.querySelector("#list-item").innerHTML = "";
			});
			this.amount();
		},
		amt_of(item) {

		},
		get count() {

		},
		amount() {
			$http.get('http://localhost:8080/rest/cart/total').then(response => {
				let totalHolder = document.querySelector("#order-total");
				if (totalHolder) {
					totalHolder.textContent = response.data;
				}
			});
		},
		saveToRestApi() {

		},
		loadFromRestApi() {
			$http.get('http://localhost:8080/rest/cart/items').then(resp => {
				let db = resp.data;
				db.map(o => {
					$http.get('http://localhost:8080/rest/pricehistory/' + o.product.id).then(resp =>
						o.price = resp.data.price);
					$http.get('http://localhost:8080/rest/cart/cost/' + o.id).then(resp =>
						o.cost = resp.data);
				});
				this.items = db;
			});
			this.amount();
		}

	}
	$scope.cart.loadFromRestApi();
	$scope.shop.loadFromRestApi();
});
