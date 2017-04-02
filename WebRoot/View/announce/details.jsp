<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 引入自定义分页标签 -->
<%@ taglib prefix="page" uri="/mvcPager"%>
<!-- 引入标准标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>公告管理</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<link href="<%=basePath%>content/bootstrap/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
	  <div class="centent">
	  <div class="title">
	    标题：${announce.announce_Title }</div>
	    <div class="author">
	    作者：${announce.manager_name }
	    </div>
	    
	    <hr/>
	    <div class="nei">
	    <span>${announce.announce_Content }</span>
	    </div>
	    
	  </div>
	</div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
	</body>
</html>
