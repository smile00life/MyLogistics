function checkinput(){
    var announce_Title=document.getElementById("announce_Title");
    var announce_Content=document.getElementById("announce_Content");
    
    if(announce_Title.value==""||announce_Title.value==null)
    {
       alert("标题不能为空!");
       return false;
    }
     if(announce_Content.value==""||announce_Content.value==null)
    {
       alert("公告能容不能为空！");
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
  
 //输入框控件。。。。表情，字体 ,位于公告内容前面
  KindEditor.ready(function(K) {
		var editor = K.create('#announce_Content', {
			resizeType : 1,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]

		});
	}); 
  
  
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
  
  
  