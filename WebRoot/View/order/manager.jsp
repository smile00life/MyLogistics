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
<title>订单管理</title>
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
						<h3>订单列表</h3>
					</div>
					<div class="innerContent">
					   <!-- 公告栏顶部 -->
					    <div class="innertop">
						<table class="sTable">
							<tr class="anntitle">
								<th><input type="checkbox" value="全选" onclick="ChooseAll()" />
								</th>
								<th>序号</th>
								<th>订单编号</th>
								<th>货物名称</th>
								<th>客户姓名</th>
								<th>下单时间</th>
								<th>订单状态</th>
								
							</tr>
							<c:forEach items="${list}" var="item">
								<tr class="oddRow">
									<td class="check_list"><input type="checkbox" id="list"
										name="list" /></td>
										<td class="list_li">${item.order_id}</td>
										<td class="list_per">${item.order_code}</td>
										<td class="list_per"><a
										href="<%=basePath%>order/details/${item.order_id}"
										title="${item.sender_user}">${item.goods_name}</a></td>
									<td class="list_name"><a
										href="<%=basePath%>order/details/${item.order_id}"
										title="${item.sender_user}">${item.sender_user}</a></td>
										
									<td class="create_time">${item.create_time}</td>
									<td class="list_per">${item.order_pay}</td>
									
									<td class="editItem">
										<ul class="editlist">
											<li class="iconEdit"><a href="<%=basePath%>order/update/${item.order_id}">编辑</a></li>
											<li class="iconDel"><a onclick="return confirm('您确认要删除当前订单吗？')" href="<%=basePath%>order/delete/${item.order_id}">删除</a></li>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</table>
						</div>
						<!-- 公告栏底部 -->
						<!-- 分页开始 -->
						<div class="innerbom">
						<form method="post" id="searchForm" action="<%=path%>/order/manager">
							<input type="hidden" name="searchInfo" value="${searchInfo}">
						</form>
						<page:createPager pageSize="${pageSize}" totalPage="${totalPage}"
							totalCount="${totalCount}" curPage="${pageNum}"
							formId="searchForm" />
							</div>
                        <!-- 分页结束 -->
						<!-- End of  #contentlist -->
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
