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
<title>二级管理中心信息查询界面</title>
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
						<h3>二级分拣中心信息查看</h3>
					</div>
					<div class="innerContent">
							<table class="aTable">
								<tr>
                                    <td class="sTitle">二级分拣中心编号:</td>
								    <td colspan="3" class="sinfo">${departmentInfo.department_id }</td>
								</tr>
								<tr>
								<td class="sTitle">所在地区:</td>
								<td colspan="3"  class="sinfo">${departmentInfo.city_name} </td>
							</tr>
							<tr>
								<td class="sTitle">二级分拣中心名称:</td>
								<td class="sinfo">${departmentInfo.department_name }</td>
							</tr>
							<tr>
								<td class="sTitle">二级管理员:</td>
								<td  class="sinfo">${departmentInfo.manager_id }</td>
                                
							</tr>
							<tr>
								<td class="sTitle">管理标识（2位）:</td>
								<td  class="sinfo">${departmentInfo.department_code }</td>
								
							</tr>
							<tr>
							    <td class="sTitle">负责人:</td>
								<td  class="sinfo">${departmentInfo.department_manager }</td>
							</tr>
							<tr>
								<td class="sTitle">联系方式1-座机:</td>
								<td colspan="3"  class="sinfo">${departmentInfo.department_tel }</td>
							</tr>
							<tr>
								<td class="sTitle">联系方式2-手机:</td>
								<td colspan="3"  class="sinfo">${departmentInfo.department_mobile }</td>
							</tr>
							<tr>
								<td class="sTitle">地址:</td>
								<td colspan="3"  class="sinfo">${departmentInfo.department_address }</td>
							</tr>
							<tr>
								<td class="sTitle">最后修改时间:</td>
								<td colspan="3"  class="sinfo">${departmentInfo.write_time }</td>
							</tr>
							<tr>
								<td class="sTitle">修改人:</td>
								<td colspan="3"  class="sinfo">${manager_name }</td>
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
