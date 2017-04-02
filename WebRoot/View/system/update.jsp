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
						<form action="<%=basePath%>system/updateSystem" method="post">
							<table class="aTable">
								<tr>
                                    <input type="hidden"  name="manager_id" value="${manager_id}"/>
                                    <input type="hidden"  name="manager_name" value="${manager_name}"/>
                                    <input type="hidden"  name="company_id" value="${systeminfo.company_id}"/>
								</tr>
								<tr>
									<td class="cinfo">公司名称</td>
                                    <td><input type="text"  name="company_name" value="${systeminfo.company_name}"/> </td>
								</tr>
								<tr>
									<td class="cinfo">识 别 码 :</td>
                                   <td> <input type="text"  name="company_code" value="${systeminfo.company_code}"/> </td>
								</tr>
								<tr>
									<td class="cinfo">营业执照号</td>
                                    <td><input type="text"  name="company_license" value="${systeminfo.company_license}"/> </td>
								</tr>
								<tr>
									<td class="cinfo">公司联系方式</td>
                                    <td><input type="text"  name="company_tel" value="${systeminfo.company_tel}"/> </td>
								</tr>
								<tr>
									<td class="cinfo">公司地址</td>
                                    <td><input type="text"  name="company_address" value="${systeminfo.company_address}"/> </td>
								</tr>
								<tr>
								    <td class="cinfo">公司简介</td>
										<td><textarea cols="20"
											name="company_content" id="company_content" style="width:500px; height:300px;">
											${systeminfo.company_content }
                                   </textarea></td>

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
