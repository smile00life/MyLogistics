function checkinput(){
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    var checkcode=document.getElementById("checkcode");
    
    var checkcode_error = document.getElementById("checkcode_error")
    var check_err = "验证码输入错误";
    if(username.value==""||username.value==null)
    {
       alert("用户名不能为空!");
       return false;
    }
     if(password.value==""||password.value==null)
    {
       alert("密码不能为空！");
       return false;
    } 
     if(checkcode.value==""||checkcode.value==null)
    	 {
    	 alert("验证码不能为空");
    	 return false;
    	 }
     if(checkcode_error.equals(check_err)){
 		alert("验证码输入错误");
 		return false;
 	  }		
    
    //删除不符合的字符
    function checkName(x) {
		var y = document.getElementById(x);
		y.onkeyup = function() {
			this.value = this.value.replace(/[^\w\.\/]/ig, '');
			
		};
		y.onafterpaste = function() {
			this.value = this.value.replace(/[^\w\.\/]/ig, '');
		};
	}
  };
  //读取表单中的数据
  function getcookie(name){
  	var arr = document.cookie.match(new RegExp("(^|)" + name + "=([^;]*)(;|$)"));
  	
  	if (arr!='')
  	{
  		return unescape(arr[2]);// unescape() 函数可对通过 escape() 编码的字符串进行解码。
  	}
  	else{
  		return null;
  	}
  	
  }
  //检测表单中是否有数据，若没有则填入保存的cookie数据
  function loadcookie(){
  	var value = getcookie("username");//获取cookie中的数据
  	
  	if(value!=''||value!=null)
  	{
  		document.getElementById("username").value = value;
  	}
  }
  function loadcookie(){
  	var value = getcookie("password");//获取cookie中的数据
  	
  	if(value!=''||value!=null)
  	{
  		document.getElementById("password").value = value;
  	}
  }
  //刷新验证码
  function reloadCode(){
	  
	  document.getElementById("check-img").src="ValidateCodeController?time"+new Date().getTime();
	}
  //页面未加载完毕时，显示图片
  function doReady()
  {
	  document.getElementById('loading').style.display='none';
	    document.getElementById('loading').style.visibility='none';
	    document.getElementById('main').style.visibility='visible';
  }
  
  