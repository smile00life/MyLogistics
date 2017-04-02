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
<title>商品信息查询界面</title>
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
						<h3>商品详情</h3>
					</div>
					<div class="innerContent">
					    
						<table>
							</tr>
							<tr>
							<td class="order_ss">商品名称：</td><td class="order_td" style="width: 200px;">${goodsinfo.goods_name }</td>
							</tr>
							<tr>
							<td class="order_ss">商品内容：</td><td class="order_td">${goodsinfo.goods_content }</td>
							
							</tr>
							<tr>
							<td class="order_ss">商品重量：</td><td class="order_td">${goodsinfo.goods_weight }</td>
							</tr>
							<tr>
							<td class="order_ss">商品类型：</td><td class="order_td" style="width: 200px;">${goodsinfo.goods_type }</td></tr>
			                
						</table>
						   
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
	</div>
	</body>
</html>
