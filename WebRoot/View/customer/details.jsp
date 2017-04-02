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
<title>客户信息查询界面</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<link href="<%=basePath%>content/bootstrap/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
	<!-- center开始 -->
	<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<h3>客户信息查询</h3>
					</div>
					<div class="innerContent">
							<span>客户编号：</span><span>${cusinfo.customer_id }</span><br/>
							<span>客户姓名：</span><span>${cusinfo.customer_name }</span><br/>
							<span>身份证号：</span><span>${cusinfo.customer_code }</span><br/>
							<span>详细地址：</span><span>${cusinfo.customer_address }</span><br/>
							<span>订单编号：</span><span>${cusinfo.order_id }</span><br/>
							<span>联系方式一(手机)：</span><span>${cusinfo.customer_tel }</span><br/>
							<span>联系方式二(座机)：</span><span>${cusinfo.customer_mobile }</span><br/>
							<span>注册时间：</span><span>${cusinfo.write_time }</span><br/>
						<!-- End of  #contentlist -->
					</div>
				</div>
				<!-- End of  #contentlist -->
			</div>
			<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
		</div>
	
	
	  <!-- center结束 -->
	</div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
	</body>
</html>
