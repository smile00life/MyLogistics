<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>发布公告</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/updatepass.js"
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
						<form action="<%=basePath%>system/updatePassword" method="post">
							<table class="aTable">
								<tr>
                                    <input type="hidden"  name="manager_id" value="${manager_id}"/>
                                    <input type="hidden"  name="username" value="${manager_name}"/>
                                    
								</tr>
								<tr>
									<td class="cpass">旧密码</td>
                                    <td><input type="text" id="password" name="password" /> </td>
								</tr>
								<tr>
									<td class="cpass">新密码</td>
                                   <td> <input type="text" id="newpassword" name="newpassword" /> </td>
								</tr>
								<tr>
									<td class="cpass">再次输入</td>
                                   <td> <input type="text" id="repass"  name="repass" /> </td>
								</tr>
								<tr class="aSubmit">
									<td><input type="submit" class="from_sub" value="保存" onclick="return checkinput()"/></td>

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
