function loadXMLDoc() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET", "TestServlet2", true);
	xmlhttp.send();
};
function ckName() {
	var xmlhttp;
	var city_name = document.getElementById("city_name").value;

	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//处理结果,onreadystatechange为当state发生变化时，触发
	xmlhttp.onreadystatechange = function() {
		//readyState位检测浏览器请求状态，0-4,五种状态,0表示一切准备就绪,1表示加载了open()已建立链接,但是还未提交,
		//2表示加载了send()，表示已经提交,3表示已经收到浏览器头部请求，其余请求正在处理中,4表示收到全部请求
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//status是判断服务器是否请求正常,200为正常,404未找到地址
			//document.getElementById("msg").innerHTML=xmlhttp.responseText;
			var msg = document.getElementById("msg");
			if (xmlhttp.responseText == "true") {
				msg.innerHTML = "<font color='red'>用户已存在</font>";
				//msg.style.color="red";
				//document.getElementById("msg").innerHTML="<font color='red'>用户已存在</font>";
			} else
				//document.getElementById("msg").innerHTML="<font color='blue'>可以使用</font>";
				msg.innerHTML = "<font color='green'>可以使用</font>";
			//msg.style.color="lime";
		}
	}
	
	//创建链接
	xmlhttp.open("get", "cost/getJsonDataByCityName?city_name="+city_name,
			true);
	
	xmlhttp.setRequestHeader("ContentType", "application/json");
	//发送请求
	xmlhttp.send();
}














