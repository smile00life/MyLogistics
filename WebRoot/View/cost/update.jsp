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
<title>修改成本信息</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>
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
						<h3>修改二级分拣中心信息</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>cost/updateCost" method="post">
							<table class="aTable">
								<tr>
                                    <%-- <input type="hidden"  name="manager_id" value="${manager_id}"/> --%>
                                    <input type="hidden"  name="manager_name" value="${manager_name}"/>
                                    <input type="hidden"  name="cost_id" value="${costinfo.cost_id }"/>
								</tr>
								<tr>
								<td class="order_ss"><span class="important">*</span>城市名称:</td>
								
								<td class="order_dd"><span>${costinfo.city_name }</span></td>
							</tr>
							<tr>
								<td class="order_ss"><span class="important">*</span>起送价格:</td>
								<td class="order_dd"><input type="text" name="cost_price"  value="${costinfo.cost_price }"/></td>
							</tr>
							<tr>
								<td class="order_ss"><span class="important">*</span>每公斤加价:</td>
								<td class="order_dd">
								     <input  type="text" name="cost_per"  value="${costinfo.cost_per }"/>
                                </td>
							</tr>
							
							<tr class="sSubmit">
								<td Colspan="4"><input type="submit" class="from_sub"
									value="保存" /></td>

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
