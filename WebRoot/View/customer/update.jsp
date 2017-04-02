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
<title>修改客户信息</title>

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
						<h3>修改客户信息</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>customer/updateCustomer" method="post">
							<table class="aTable">
								<tr>
                                    <%-- <input type="hidden"  name="manager_id" value="${manager_id}"/> --%>
                                    <input type="hidden"  name="manager_name" value="${manager_name}"/>
                                    <input type="hidden"  name="customer_id" value="${cusinfo.customer_id}"/>
                                    
								</tr>
								<tr>
								<td class="sTitle"><span class="important">*</span>客户姓名:</td>
								
								<td ><input type="text" name="customer_name" style="width: 400px;" value="${cusinfo.customer_name}"/></td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>身份证号:</td>
								<td ><input type="text" name="customer_code" style="width: 400px;" value="${cusinfo.customer_code}"/></td>
							</tr>
							<tr>
								<span class="important">*</span><td class="sTitle">居住地址:</td>
								<td >
								     <input type="text" name="customer_address" style="width: 400px;" value="${cusinfo.customer_address}"/>
                                </td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>订单编号:</td>
								<td ><input type="text" name="order_id" style="width: 400px;" value="${cusinfo.order_id}"/></td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>联系方式一(手机):</td>
								<td ><input type="text" name="customer_tel" style="width: 400px;" value="${cusinfo.customer_tel}"/></td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>联系方式二(座机):</td>
								<td ><input type="text" name="customer_mobile" style="width: 400px;" value="${cusinfo.customer_mobile}"/></td>
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
