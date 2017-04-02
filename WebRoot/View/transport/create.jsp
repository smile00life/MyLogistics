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
<title>添加车辆信息</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/createtest.js"
	type="text/javascript"></script>
</head>
<body>
	<!-- header开始 -->
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
		<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<h3>添加车辆信息</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>transport/getCreateTransport" method="post">
							<table class="aTable">
								<tr>
									<td class="aTitle">车辆名称：</td>
                                   <td > <input style="width: 200px;" type="text"  name="transport_name" id="transport_name"/></td>
								<td class="aTitle">车辆类型：</td>
									<td><input 
										style="width: 200px;"  id="transport_type" name="transport_type"
										type="text"  /></td>
								</tr>
								
								<tr>
									<td class="aTitle">车牌号：</td>
									<td><input 
										style="width: 200px;"  id="plate_number" name="plate_number"
										type="text"  /></td>
									<td>司机/派送员：</td>
									<td>
									<select name="transport_driver" id="transport_driver">
									<option value="1" selected >请选择</option>
									<c:forEach var="ci" items="${workersender }">
									<option value='<c:out value="${ci.worker_name}" />' >${ci.worker_name }</option>
									</c:forEach>
									</select>
									</td>
									<!-- <td><input 
										style="width: 200px;"  id="transport_driver" name="transport_driver"
										type="text"  /></td> -->

								</tr>
								<tr><td>车辆详细信息：</td>
									<td colspan="4"><textarea 
										style="width: 465px;height:200px"  id="transport_info" name="transport_info"
										  ></textarea></td>

								</tr>
								<tr class="aSubmit" >
									<td align="center"  colspan="4"><input type="submit" class="from_sub" value="提交" onclick="return checkinput()"/></td>

								</tr>
							</table>
						</form>
						<!-- End of  #contentlist -->
					</div>
				</div>
				<!-- End of  #contentlist -->
			</div>
			<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
		</div>
	</div>
	<!-- footer开始 -->
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
</body>
</html>
