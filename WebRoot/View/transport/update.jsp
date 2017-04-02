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
<title>更新公告</title>
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
						<h3>发布公告</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>transport/updateTransport" method="post">
							<table class="aTable">
								<tr>
                                    <input type="hidden"  name="transport_id" id="transport_id" value="${transportinfo.transport_id}"/>
								</tr>
								<tr>
									<td class="aTitle">车辆名称：</td>
                                    
                                    <td><input type="text" style="width: 200px;"  name="transport_name" id="transport_name" value="${transportinfo.transport_name}"/></td>
								    <td class="aTitle">车辆类型：</td>
									<td><input 
										style="width: 200px;"  id="transport_type" name="transport_type"
										type="text" value="${transportinfo.transport_type}" /></td>
								</tr>
								
								<tr>
									<td class="aTitle">车牌号：</td>
									<td><input 
										style="width: 200px;"  id="plate_number" name="plate_number"
										type="text"  value="${transportinfo.plate_number}"/></td>
                                     <td>司机/派送员：</td>
									<td><select name="transport_driver" id="transport_driver">
									<option value="${transportinfo.transport_driver}" selected>${transportinfo.transport_driver}</option>
									   
										<c:forEach var="ci" items="${workersender }">
										<option value='<c:out value="${ci.worker_name}" />' >${ci.worker_name }</option>
										</c:forEach>
									</select></td>
								</tr>
								<tr>

								</tr>
								<tr><td >车辆详细信息：</td>
									<td colspan="4"><textarea 
										style="width: 465px;height:200px"  id="transport_info" name="transport_info">
										${transportinfo.transport_info }
										</textarea></td>

								</tr>
								
								<tr class="aSubmit">
									<td colspan="4" align="center"><input style="height:25px;width:60px;" type="submit" class="from_sub" value="保存" onclick="return checkinput()"/></td>

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
