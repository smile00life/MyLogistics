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
<title>分拣中心管理</title>
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
						<h3>二级分拣中心列表</h3>
					</div>
					<div class="innerContent">
					   <!-- 公告栏顶部 -->
					    <div class="innertop">
						<table class="sTable">
							<tr class="anntitle">
								<th><input type="checkbox" value="全选" onclick="ChooseAll()" />
								</th>
								<th>机构名称</th>
								<th>管理员</th>
								<th>更新时间</th>
							</tr>
							<c:forEach items="${list}" var="item">
								<tr class="oddRow">
									<td class="check_list"><input type="checkbox" id="list"
										name="list" /></td>
									<td class="list_title"><a
										href="<%=basePath%>department/details/${item.department_id}"
										title="${item.department_address}">${item.department_name}</a></td>
										<td class="list_mana">${item.department_manager}</td>
									<td class="list_time">${item.write_time}</td>
									<td class="editItem">
										<ul class="editlist">
											<li class="iconEdit"><a href="<%=basePath%>department/update/${item.department_id}">编辑</a></li>
											<li class="iconDel"><a onclick="return confirm('您确认要删除当前数据吗？')" href="<%=basePath%>department/delete/${item.department_id}">删除</a></li>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</table>
						</div>
						<!-- 公告栏底部 -->
						<!-- 分页开始 -->
						<div class="innerbom">
						<form method="post" id="searchForm" action="<%=path%>/department/managertwo">
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
