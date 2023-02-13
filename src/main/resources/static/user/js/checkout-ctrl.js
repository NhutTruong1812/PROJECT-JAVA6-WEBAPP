const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {

	$scope.address = [];

	$scope.addressEntity = [];

	$scope.carts =[];

	$scope.form = {};
  
	$scope.initialize = function() {
		
		//Load address  
		$http.get("/rest/address").then(resp => {
			$scope.address = resp.data;
		})
		
		//loadCart 
		$http.get('http://localhost:8080/rest/checkout/carts').then(resp => {
			let db = resp.data;
			db.map(o => {
				$http.get('http://localhost:8080/rest/pricehistory/' + o.product.id).then(resp =>
					o.price = resp.data);
				$http.get('http://localhost:8080/rest/cart/cost/' + o.id).then(resp =>
					o.cost = resp.data);
			});
			
			this.carts = db;
			console.log(this.carts.length) 
			if(this.carts.length <= 0){
			 	location.href= 'http://localhost:8080/home/cart',
				alert("Chưa có sản phẩm nào để thanh toán");
			} 
		})
		//load tong tien total 
			$http.get('http://localhost:8080/rest/cart/total').then(response => {
				let totalHolder = document.querySelector("#order-total");
				if (totalHolder) {
					totalHolder.textContent = response.data;
				}
			});
		  
	}

	$scope.initialize();

	$scope.choose = function(address) {
		$scope.form = angular.copy(address);
	}

	$scope.delete = function(item) {
		$http.delete(`/rest/address/${item.id}`).then(resp => {
			var index = $scope.address.findIndex(a => a.id == item.id);
			$scope.address.splice(index, 1);
			alert("Đã xóa địa chỉ")
		})
			.catch(error => {
				alert("Lỗi")
			});
	} 
	
	//Checkout
	$scope.checkout = function(cartItem) {
		
		var item = angular.copy($scope.form);  
		
		
		var saveAddress = angular.copy($scope.saveAddress);
		 	 
		if(item.fullname != undefined && item.address != undefined && item.phoneNumber != undefined ){
			
			//create address 
			//alert("Add Address: "+ saveAddress); 
			
			if(saveAddress == true){ 
				$http.post('/rest/address', item).then(resp =>{/*alert("Thêm địa chỉ thành công");*/})
			} 
			
			//create order
			var orderId;
			$http.post(`/rest/checkout/order`, item).then(resp =>{
				this.orderId = resp.data; 
				//alert("Thêm order thành công! " + this.orderId.id);
				//create orderDetail
				cartItem.map(od=>{ 
					let orderDetail={
						order:this.orderId,
						product:od.product,
						priceHistory:od.price,
						quantity:od.quantity
					}
					//alert("OD: "+ orderDetail.order.id +" P: "+orderDetail.product.id+" PR: "+orderDetail.priceHistory.id+ " Q: "+ orderDetail.quantity); 
					$http.post('/rest/checkout/order/detail', orderDetail).then(resp =>{
					
					})
					  
					//Update quantity product
					$http.get(`/rest/product/${od.product.id}`).then(resp=>{
							let product = resp.data;
							if(product.quantity > od.quantity){
								product.quantity = product.quantity - od.quantity;
							}else{
								product.quantity = 0;
								product.deleted = true;
							}
							$http.put(`/rest/product/${product.id}`, product).then(resp=>{
								
							}).catch(Error=>{
								alert("Lỗi Xử Lý Số Lượng")
							})
					})
					
					
					//clear cart
					$http.delete(`/rest/checkout/cart/${od.id}`).then(resp => {
						var index = $scope.carts.findIndex(ci => ci.id == od.id);
						$scope.carts.splice(index, 1);
						//alert("Đã xóa item " + od.id)
					})
				})
				//
				alert('Thanh Toán Thành Công!')
				
				location.href='http://localhost:8080/home/cart';
			})
			
			
			
		
			
			 
		}else{
			alert("Vui lòng kiểm tra lại thông tin! Một số thông tin chưa được nhập");
		} 
	}


})
