<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Save for Web Slices (设计.psd) -->
<div id="__01" class="mainbody">
	<div class="header">
	  <div class="header_log">
		<div class="header_logos"></div>
		<div class="header_logInfo">
				欢迎您 <strong>${welcome }</strong>! [ <a href='<%=basePath%>LogOut' onclick='return confirm("亲！真要忍心离开吗？")'>安全退出</a> ] |
				<a href="admin/index">首页</a>
		</div>
	  </div>
	</div>
	<div class="manager_nav_bg">
		<div class="manager_nav">
			<ul id="menu">
				<c:forEach var="menu" items="${siderlist }"> 
					<li><a href="${menu.menuPath }">${menu.menuContent }</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>