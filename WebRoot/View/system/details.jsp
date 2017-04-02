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
		<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<h3>公司信息维护</h3>
					</div>
					<div class="innerContent">
					   <!-- 信息展示顶部 -->
						<div class="innertop">
							  <div class="nei">
							   <p>公司名称：${systeminfo.company_name }</p>
							   <p>识别码(2位):${systeminfo.company_code }</p>
							   <p>营业执照：${systeminfo.company_license }</p>
							   <p>公司地址:${systeminfo.company_address }</p>
							   <p>联系方式：${systeminfo.company_tel }</p>
							   <p>公司介绍:${systeminfo.company_content }</p>
							   </div>
	 
	                   </div>
	                 </div>
	              </div>
	            </div>
	            <!-- 管理目录 -->
	          <jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
	          </div>
	       </div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
	</body>
</html>
