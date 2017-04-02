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
<title>成本管理</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<link href="<%=basePath%>content/bootstrap/bootstrap.min.css"
	rel="stylesheet">
	<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>content/js/cost.js"
    type="text/javascript"></script>
  
</head>
<script type="text/javascript">
	//全选
function selectAll(obj){
for(var i = 0;i<obj.elements.length;i++)
if(obj.elements[i].type == "checkbox")
obj.elements[i].checked = true;
}
//反选
function selectOther(obj){
for(var i = 0;i<obj.elements.length;i++)
if(obj.elements[i].type == "checkbox" ){
	if(!obj.elements[i].checked)
		obj.elements[i].checked = true;
	else
		obj.elements[i].checked = false;

}
}
//取消全部
function clearAll(obj){
for(var i = 0;i<obj.elements.length;i++)
if(obj.elements[i].type == "checkbox")
obj.elements[i].checked = false;
}
</script>
<script type="text/javascript">
$(document).ready(function () {
	//$(function() {--修改这里
		$("#sousuo").keyup(
				function() { //当框改变时激活
					var selec = $(this).val(); //搜索框的值city_name
					$("#sousuo_la").show();
					//var div = document.getElementById("sousuo_la");
					
					//selec = $.trim(selec);//去掉前后空格
					 if($.trim(selec)=="")
					{ 
						 
						// $("#sousuo_la").html("<font color='red'>请输入！</font>");
						 $("#sousuo_la").empty(); //清空,保留格式，remove是连同格式一起清空
						 $("#sousuo_la").hide();
						
						}else
							{
							
							var url ="<%=basePath%>cost/getCostInfoName";   //参数依次类型(action,Controller,area）            
							var args={"city_name" : selec};
							
						
	                    //上面我们只用第一个参数action，也就是方法的请求地址
						//$("#msg").empty(); //清空,保留格式，remove是连同格式一起清空
						
							//如果选择的是正确的城市对应的id数字，则查询对应的区县并赋值到第二个下拉框
						$.getJSON(url, args, function(data) { 
							
								//$("#sousuo_la").text(data);
								
							$("#sousuo_la").html(data);
						});
							
							}
				});
	});
	/* //获得焦点显示框
	function show_sousuo()
	{
		$("#sousuo_la").show();
	}
	//失去焦点隐藏
	function hide_sousuo()
	{
		$("#sousuo_la").hide();
	} */
	
	
	//鼠标经过
	function background_over(div)
	{
	   	div.style.backgroundColor = "gray";
	
	
	};
	//鼠标离开
	function background_out(div)
	{
	   	div.style.backgroundColor = "white";
	};
	//点击填充
	
	function write_text(div)
	{
		var selec = document.getElementById("sousuo");
		selec.value = div.innerHTML;
		div.parentNode.style.display="none";
		//$("#sousuo_la").hide();
	};
	
	
	function buttoms()
	{
		//var selec = document.getElementById("sousuo");
	
		var selec = $("#sousuo").val();
		//alert(selec);
			
		//$("#costinfo").empty();
		//$("#costinfo").remove();
		//$("#costinfo").html("<tr><td>ss</td></tr>");
		//$("#cost_id").text("sss");
		
		$("#innerbom").hide();
	    if($.trim(selec)=="")
		{  
			// $("#sousuo_la").html("<font color='red'>请输入！</font>");
			alert("请输入城市名！");
			
			}else
				{
				
				var url ="<%=basePath%>cost/getCostInfoNameList";   //参数依次类型(action,Controller,area）            
				var args={"city_name" : selec};
			//$("#msg").empty(); //清空,保留格式，remove是连同格式一起清空
			$.getJSON(url, args, function(data) { 
				
					
				$("#costinfo").html(data);
				
				//$("#costinfo").text(data);
				
			});
				
				}
	};
	
	/* //此方法可以查询出当前分页下的指定城市
	function buttoms2()
	{
			//var selec = document.getElementById("sousuo");
				
	    var selec = $("#sousuo").val();		
		if($.trim(selec)!=""){
					
			$("table tr:not('#theader')").hide().filter(":contains('"+selec+"')").show();
			
		}
	    else{        
		
	    	$("table tr:not('#theader')").css("background","#fff").show(); 
		} 
			
	}; */
		
</script>

<body>
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
		<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<div class="contextBoxLeft">成本列表</div>
						<div class="contextBoxRight">
						
						<input class="contextBoxInput"   type="text" id="sousuo" placeholder="输入城市名" name="sousou" />
						<input class="contextBoxsubmit" id="button" onclick="buttoms()" type="button" value="搜索" />
						
						</div>
						<div class="sousuo_la" style="display:none;" id="sousuo_la" name="sousuo_la"></div>
					</div>
					<div class="innerContent">
					   <!-- 公告栏顶部 -->
					    <div class="innertop">
						<table class="sTable" >
							<tr class="anntitle" id="theader">
								<th><input type="checkbox" value="全选" onclick="ChooseAll()" />
								</th>
								<th>编号</th>
								<th>城市名称</th>
								<th>起送价格</th>
								<th>每公斤加价</th>
								<th>最后修改时间</th>
								
							</tr>
                             </table>
                             <table id="costinfo" class="sTable">							
							<c:forEach items="${list}"  var="item">
								<tr class="oddRow">
									<td class="check_list"><input type="checkbox" id="list"
										name="list" /></td>
										<td class="list_per" id="cost_id">${item.cost_id}</td>
									<td class="list_name" id="city_name"><a
										href="<%=basePath%>cost/details/${item.cost_id}"
										title="${item.city_name}">${item.city_name}</a></td>
										<td class="list_per" id="cost_price">${item.cost_price}</td>
										<td class="list_per" id="cost_per">${item.cost_per}</td>
									<td class="list_time" id="write_time">${item.write_time}</td>
									
									<td class="editItem">
										<ul class="editlist">
											<li class="iconEdit"><a href="<%=basePath%>cost/update/${item.cost_id}">编辑</a></li>
											<li class="iconDel"><a onclick="return confirm('您确认要删除当前数据吗？')" href="<%=basePath%>cost/delete/${item.cost_id}">删除</a></li>
										</ul>
									</td>
								</tr>
							</c:forEach>
							
						</table>
						</div>
						<!-- 公告栏底部 -->
						<!-- 分页开始 -->
						<div class="innerbom" id="innerbom">
						<form method="post" id="searchForm" action="<%=path%>/cost/manager">
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
