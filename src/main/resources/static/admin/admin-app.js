app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
		.when("/category", {
			templateUrl: "/admin/category/index.html",
			controller: "category-ctrl"
		})
		.when("/product", {
			templateUrl: "/admin/product/index.html",
			controller: "product-ctrl"
		})
		.when("/user", {
			templateUrl: "/admin/user/index.html",
			controller: "user-ctrl"
		})
		.when("/authority", {
			templateUrl: "/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/order", {
			templateUrl: "/admin/order/index.html",
			controller: "order-ctrl"
		})
		.when("/reportUser", {
			templateUrl: "/admin/report/reportUser.html"
		})
		.when("/reportProduct", {
			templateUrl: "/admin/report/reportProduct.html"
		})
		.when("/reportOrder", {
			templateUrl: "/admin/report/reportOrder.html"
		})
		.otherwise({
			templateUrl: "/admin/home/index.html"
		});
});

let rootUrl = "http://localhost:8080";
/*
app.controller("menu-ctrl", function($scope, $http) {

	//Load current User
	$scope.currentUser = null;
	$scope.loadCurrentUser = async function() {
		await $http.get(rootUrl + "/rest/user/current").then(response => {
			console.log("current User");
			console.log(response.data)
			$scope.currentUser = response.data;
		});
	}
	//Load authorities of current User
	$scope.currentAuthorities = null;
	$scope.loadCurrentAuthorities = async function() {
		console.log($scope.currentUser)
		await $http.get(rootUrl + "/rest/authority/user/" + $scope.currentUser.id).then(response => {
			$scope.currentAuthorities = response.data;
		});
	}

	$scope.isCurrentUserInRole = function(roleId) {
		let au = $scope.currentAuthorities.find(item => {
			if (item.role.id == roleId) {
				return au;
			}
		});
		if (au == null) {
			return false;
		}
		return true;
	}
	$scope.init = function() {
		$scope.loadCurrentUser();
		$scope.loadCurrentAuthorities();
	}
	$scope.init();
});*/


app.controller("myctrl1z", function($scope, $http) {
	$scope.demo1z = 'doanh thu';
	$scope.tkdoanhthu = [];
	$scope.tknguoidung = [];
	$scope.tksanpham = [];
	$http.get("http://localhost:8080/rest/report/tkdoanhthu")
		.then(resp => {
			$scope.tkdoanhthu = resp.data;
			console.log("data tkdoanhthu >> ", resp.data);
		})
	$http.get("http://localhost:8080/rest/report/tknguoidung")
		.then(resp => {
			$scope.tknguoidung = resp.data;
			console.log("data tknguoidung >> ", resp.data);
		})
	$http.get("http://localhost:8080/rest/report/tksanpham")
		.then(resp => {
			$scope.tksanpham = resp.data;
			console.log("data tksanpham >> ", resp.data);
		})
	$scope.gettime = function(idz1, idz2, tknao) {
		var stt = document.getElementById(idz1);
		var end = document.getElementById(idz2);
		//
		var valuestt = stt.value;
		var valueend = end.value;
		var sttout = '';
		var endout = '';
		//
		var datestt = new Date(valuestt);
		var dateend = new Date(valueend);
		if (dateend >= datestt) {
			var dds = valuestt.substring(8, 10);
			var MMs = valuestt.substring(5, 7);
			var yyyys = valuestt.substring(0, 4);
			var HHs = valuestt.substring(11, 13);
			var mms = valuestt.substring(14, 16);
			var sss = '00';
			sttout = yyyys + '-' + MMs + '-' + dds + '@' + HHs + ':' + mms
				+ ':' + sss;
			//
			var dde = valueend.substring(8, 10);
			var MMe = valueend.substring(5, 7);
			var yyyye = valueend.substring(0, 4);
			var HHe = valueend.substring(11, 13);
			var mme = valueend.substring(14, 16);
			var sse = '00';
			endout = yyyye + '-' + MMe + '-' + dde + '@' + HHe + ':' + mme
				+ ':' + sse;
		} else {
			var now = new Date();
			var MMx = (now.getMonth() + 1) < 10 ? '0' + (now.getMonth() + 1)
				: now.getMonth() + 1;
			var ddx = (now.getDate()) < 10 ? '0' + (now.getDate()) : now
				.getDate();
			var hhx = String(now.getHours()).length < 2 ? '0' + now.getHours()
				: now.getHours();
			var mmx = (now.getMinutes()) < 10 ? '0' + (now.getMinutes()) : now
				.getMinutes();
			valuex = now.getFullYear() + '-' + MMx + '-' + ddx + 'T' + hhx
				+ ':' + mmx;
			//
			stt.value = valuex;
			end.value = valuex;
			valuestt = stt.value;
			valueend = end.value;
			//
			var dds = valuestt.substring(8, 10);
			var MMs = valuestt.substring(5, 7);
			var yyyys = valuestt.substring(0, 4);
			var HHs = valuestt.substring(11, 13);
			var mms = valuestt.substring(14, 16);
			var sss = '00';
			sttout = yyyys + '-' + MMs + '-' + dds + '@' + HHs + ':' + mms
				+ ':' + sss;
			//
			var dde = valueend.substring(8, 10);
			var MMe = valueend.substring(5, 7);
			var yyyye = valueend.substring(0, 4);
			var HHe = valueend.substring(11, 13);
			var mme = valueend.substring(14, 16);
			var sse = '00';
			endout = yyyye + '-' + MMe + '-' + dde + '@' + HHe + ':' + mme
				+ ':' + sse;
		}
		var outurl = '';
		console.log('tknao >> ', tknao)
		if (tknao == 'doanhthu') {
			outurl = 'http://localhost:8080/rest/report/tkdoanhthu?sttout=' + sttout + '&endout=' + endout;
			$http.get(outurl)
				.then(resp => {
					$scope.tkdoanhthu = resp.data;
					console.log("data tkdoanhthu 2 >> ", resp.data);
				})
		} else if (tknao == 'nguoidung') {
			outurl = 'http://localhost:8080/rest/report/tknguoidung?sttout=' + sttout + '&endout=' + endout;
			$http.get(outurl)
				.then(resp => {
					$scope.tknguoidung = resp.data;
					console.log("data tknguoidung 2 >> ", resp.data);
				})
		}
		console.log('outurl >> ', outurl)
	}
})



//