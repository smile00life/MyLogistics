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
<title>成本信息查询界面</title>
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
						<h3>成本计算规则</h3>
					</div>
					<div class="innerContent">
							
						<table>
					       <tr>
						   <td class="order_ds">当前城市为：</td><td class="order_dd" style="width: 200px;">${costinfo.city_name }</td></tr>
						  <tr> <td class="order_ds">一公斤以内的起送价格为：</td><td class="order_dd" style="width: 200px;">${costinfo.cost_price }</td>
							</tr>
						
							<tr>
							<td class="order_ds">超过一公斤，每公斤加价：</td><td class="order_dd">${costinfo.cost_per }</td></tr>
							
							</table>
							<table>
							<tr><td  colspan="4">价格计算规则为：</td></tr>
							<tr>
							<td>1、</td><td style="width:100px;">若未超过1公斤</td><td class="order_dd">总价格=（起送价${costinfo.cost_price } ）</td>
							</tr>
						    <tr>
							<td>2、</td><td>若超过1公斤</td><td class="order_dd">总价格=（起送价${costinfo.cost_price } ）+ （每公斤加价${costinfo.cost_per }）*（物品总重量kg-2kg）</td>
							</tr>
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
	</body>
</html>
