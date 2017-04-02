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
<title>车辆详情</title>
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
						<h3>车辆详情</h3>
					</div>
					<div class="innerContent">
					  
					    <table>
					       <tr>
							
						   <td class="order_ss">车辆名称：</td><td  style="width: 200px;">${transportinfo.transport_name }</td>
						   <td class="order_ss">车辆类型：</td><td  style="width: 200px;height:50px;">${transportinfo.transport_type  }</td>
							</tr>
							<tr>
							
						   <td class="order_ss">车牌号：</td><td  style="width: 200px;">${transportinfo.plate_number  }</td>
						   <td class="order_ss">司机/派送员：</td><td  style="width: 200px;height:50px;">${transportinfo.transport_driver  }</td>
							</tr>
							<tr>
							<td class="order_ss">入库时间：</td><td style="width: 200px;height:50px;">${transportinfo.write_time }</td>
						   </tr>
						   </table>
						   <span >车辆详细信息：</span>
						   <span >${transportinfo.transport_info  }</span>
	           
	                </div>
	           </div>
	           <!-- End of  #contentlist -->
	       </div>
	<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
	</div>
</div>

<jsp:include page="../shared/manager_footer.jsp"></jsp:include>

</body>
</html>
