<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8" content="text/html" http-equiv="Content-type">
<title>新增成本信息</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/cost.js"
    type="text/javascript"></script>
  
    <script type="text/javascript" src="<%=basePath%>content/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>content/js/messages_zh.js"></script>

<style>
.error{
	color:red;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
	//$(function() {--修改这里
		$("#city_name").change(
				function() { //当城市选项改变时激活
					var selec = $(this).val(); //获取下拉城市选项改变的值city_id
					
					//selec = $.trim(selec);//去掉前后空格
					 if($(this).val() == "")
					{ 
						 
						 $("#msg").html("<font color='red'>不能为空</font>");
						 //$("#msg").empty(); //清空,保留格式，remove是连同格式一起清空
						
						}else
							{
							
							var url ="<%=basePath%>cost/getJsonDataByCityName";   //参数依次类型(action,Controller,area）            
							var args={"city_name" : selec};
							
						
	                    //上面我们只用第一个参数action，也就是方法的请求地址
						//$("#msg").empty(); //清空,保留格式，remove是连同格式一起清空
						
							//如果选择的是正确的城市对应的id数字，则查询对应的区县并赋值到第二个下拉框
						$.getJSON(url, args, function(data) { 
								   $("#msg").html(data);
						});
							
							}
				});
	});
</script>
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
						<h3>新增成本信息</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>cost/createCost" method="post" id="signupForm">
							<table class="aTable">
								<tr>
                                    <%-- <input type="hidden"  name="manager_id" value="${manager_id}"/> --%>
                                    <input type="hidden" name="manager_name" value="${manager_name}"/>
                                    
								</tr>
								<tr>
								<td class="order_ss"><span class="important">*</span>城市名称:</td>
								
								<td class="order_dd"><input class="order_dds" type="text"  name="city_name" id="city_name"  required minlength="2"/></td><td><span id="msg"></span></td>
							</tr>
							<tr>
								<td class="order_ss"><span class="important">*</span>起送价格:</td>
								<td class="order_dd"><input class="order_dds" type="text"  name="cost_price" id="cost_price" style="width: 200px;" /></td><td><span id="msg"></span></td>
							</tr>
							<tr><td class="order_ss">
								<span class="important">*</span>每公斤加价:</td>
								<td class="order_dd">
								     <input class="order_dds" type="text"  name="cost_per" id="cost_per" style="width: 200px;" />
                                </td>
                                <td><span id="msg"></span></td>
							</tr>
							
							<tr class="sSubmit">
								<td Colspan="4"><input type="submit" class="from_sub"
									value="保存" onclick="return checkinputs()"/></td>

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
