<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 引入标准标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>维护公司信息</title>

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
						<h3>公司信息维护</h3>
					</div>
					<div class="innerContent">
					   <!-- 信息展示顶部 -->
						<div class="innertop">
							<table class="aTable">
								<tr class="anntitle">
								<th>公司名称</th>
								<th>公司简介</th>
								<th>发布时间</th>
								<th>最后修改者</th>
							</tr>
								<c:forEach items="${systeminfo}" var="item">
								<tr class="oddRow">
									
									<td class="list_ss"><a
										href="<%=basePath%>system/details/${item.company_id}"
										title="${item.company_name}">${item.company_name}</a></td>
									
									<td class="list_mm">
									<a href="<%=basePath%>system/details/${item.company_id}"
										title="${item.company_content}">${item.company_content}</a></td>
									<td class="list_tt">${item.write_time}</td>
									<td class="autho">${manager_name}</td>
									<td class="editItem">
										<ul class="editlist">
											<li class="iconEdit"><a href="<%=basePath%>system/update/${item.company_id}">编辑</a></li>
											
										</ul>
									</td>
								</tr>
							</c:forEach>
							</table>
							</div>
						<!-- 信息展示底部 -->
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
