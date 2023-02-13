function setMinMax(element, temp) {
	var outurl = 'http://localhost:8080/home/shop/minmax';
	var value = element.value;
	if (temp == 'max') {
		window.location.href = outurl + '?shop_max=' + (value == '' ? -1 : value);
	} else if (temp == 'min') {
		window.location.href = outurl + '?shop_min=' + (value == '' ? -1 : value);
	}
}
var min = document.getElementById('min-pricezz');
var max = document.getElementById('max-pricezz');
var allItems = document.getElementsByClassName('card-a');
//
function minMax() {
	var minTemp = min.value;
	var maxTemp = max.value;
	if (minTemp == '' || minTemp == -1) {
		minTemp = 0;
		min.value = '';
	}
	if (maxTemp == '' || maxTemp == -1) {
		maxTemp = 999999999999;
		max.value = '';
	}
	var size = allItems.length;
	for (let i = 0; i < size; i++) {
		var price = allItems[i].getElementsByClassName('card-a-price')[0].innerHTML;
		if (Number(price) < Number(minTemp) || Number(price) > Number(maxTemp)) {
			allItems[i].parentElement.parentElement.style.display = 'none';
		} else {
			allItems[i].parentElement.parentElement.style.display = 'block';
		}
	}
}
min.addEventListener("keypress", function(event) {
	if (event.key === "Enter") {
		minMax();
		setMinMax(min, 'min');
	}
});
max.addEventListener("keypress", function(event) {
	if (event.key === "Enter") {
		minMax();
		setMinMax(max, 'max');
	}
});     	