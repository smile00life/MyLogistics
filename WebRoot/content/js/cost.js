function checkinputs() {
		var city_name = document.getElementById("city_name");
		var cost_price = document.getElementById("cost_price");
		var cost_per = document.getElementById("cost_per");
		
		if (city_name.value == "" || city_name.value == null) {
			alert("城市名不能为空！");
			return false;
		}

		if (cost_price.value == "" || cost_price.value == null) {
			alert("起送价不能为空！");
			return false;
		}
		if (cost_per.value == "" || cost_per.value == null) {
			alert("请输入每公斤加价");
			return false;
		}
	}
//删除不符合的字符
function checkNames(x) {
	var y = document.getElementById(x);
	y.onkeyup = function() {
		this.value = this.value.replace(/[^\w\.\/]/ig, '');
		
	};
	y.onafterpaste = function() {
		this.value = this.value.replace(/[^\w\.\/]/ig, '');
	};
}