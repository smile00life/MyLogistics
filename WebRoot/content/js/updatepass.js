function checkinput(){
    var pass=document.getElementById("password");
    var newpass=document.getElementById("newpassword");
    var repass=document.getElementById("repass");
    var username=document.getElementById("username");
    
    if(username.value==""||username.value==null)
    {
       alert("用户名不能为空!");
       return false;
    }
    if(pass.value==""||pass.value==null)
    {
       alert("旧密码不能为空!");
       return false;
    }
     if(newpass.value==""||newpass.value==null)
    {
       alert("新密码不能为空！");
       return false;
    } 
     if(repass.value==""||repass.value==null)
     {
        alert("重复密码不能为空！");
        return false;
     }
     if(pass.value==newpass.value)
     {
     	alert("新密码和旧密码不能相同，请重新输入！"); 
     	return false;
     }
     if(repass.value!=newpass.value)
    {
    	alert("两次输入密码不一致，请重新输入！"); 
    	return false;
    }
  
}  
     
   
  
  
  