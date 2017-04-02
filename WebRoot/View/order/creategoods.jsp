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
<title>添加商品</title>

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
						<h3>添加商品</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>order/createGoods" method="post">
							<table class="aTable">
								<tr>
                                    <%-- <input type="hidden"  name="manager_id" value="${manager_id}"/> --%>
                                    <input type="hidden"  name="manager_name" value="${manager_name}"/>
                                    
								</tr>
								<tr>
								<td class="sTitle"><span class="important">*</span>商品名称:</td>
								<td colspan="3"><input type="text" name="goods_name" style="width: 400px;" /></td>
							    </tr>
								
							    <tr>
								<td class="sTitle"><span class="important">*</span>商品内容:</td>
								<td colspan="3"><input type="text" name="goods_content" style="width: 400px;" /></td>
							    </tr>
							    <tr>
								<td class="sTitle"><span class="important">*</span>商品重量:</td>
								<td colspan="3"><input type="text" name="goods_weight" style="width: 400px;" /></td>
							    </tr>
								<tr>
								
							<tr>
								<td class="sTitle"><span class="important">*</span>商品类型:</td>
								<td colspan="3"><input type="text" name="goods_type" style="width: 400px;" /></td>
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
