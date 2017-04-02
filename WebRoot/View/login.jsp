<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>后台管理登录页面</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>content/css/login.css"/>
<script src="<%=basePath%>content/js/logintest.js"
	type="text/javascript"></script>
</head>
<body onload="doReady()">
		<div class="container" id="main">
			
			<div class="login">
				<div class="login_hh"> <h1>后台管理登录</h1></div>
				<div class="login_left">
					<form action="validateLogin" method="post">
						<ul>
							<li>用户名</li>
							<li><div class="username"><img src="<%=basePath %>content/images/login/user.png" style="height: 30px;width: 30px;margin-top: 5px;"/>
							    <input class="input_username" type="text" name="username" placeholder="用户名" required /></div></li>
							<li>密码</li>
							<li><div class="username"><img src="<%=basePath %>content/images/login/pass.png" style="height: 30px;width: 30px;margin-top: 5px;"/>
							    <input class="input_username" type="password" name="password" placeholder="密码" required /></div></li>
							<li>验证码</li>
							<li><div class="yan"><img src="<%=basePath %>content/images/login/pass.png" style="height: 30px;width: 30px;margin-top: 5px;"/>
								<input class="input_yan" type="text" name="checkcode" placeholder="验证码" required /></div>
							    <span style="position: absolute;left: 150px;top: 210px;cursor: pointer;"> <a class="shuaxin" onclick="reloadCode()" style="margin-left:10px;">刷新 
							    <img id="check-img" class="check-img" alt="" src="ValidateCodeController" />
							   </a></span>
							</li>
								<li><input type="checkbox" name="checkbox" />记住我</li>
							<li style="margin-top: 20px;"><input type="submit" class="submit" value="登录" onclick="return checkinput()"/></li>
							<li style="margin-top: 10px;width: 250px;font-size: 16px;">
								<span style="width: 100px;"><a href="javascript:void(0)" >忘记密码？</a></span>&nbsp;&nbsp;&nbsp;
				        		<span><a href="javascript:void(0)" >新用户注册</a>
								</span>
							</li>
						</ul>
						
						
					</form>
				</div>
				<!--login_left--end-->
				<!--隔条-->
				<div style="width: 1px;height: 400px;background-color:#843534;position: absolute;top: 100px;left: 300px;"></div>
				<!--ogin_right-start-->
				<div class="login_right">
					
					<div class="announce">最新公告</div>
					<ul>
					<c:forEach var="ul" items="${announceInfos}">
						
						<li><font style="font-size: 18px;color: #F0AD4E;margin-right: 10px;">></font><a>${ul.announce_Title }</a></li>
					</c:forEach>
					</ul>
				</div>
				<!--ogin_right-end-->
			</div>
			
		</div>
		
		<div id="loading"  style="position:absolute;top:330px;left:550px;height:25px;width:50px;"><img src="<%=basePath %>content/images/loading.gif"/></div>  <!-- 未加载完毕时显示图片 -->
	</body>
</body>
</html>
