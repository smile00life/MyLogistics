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
<title>新增订单</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/createtest.js"
	type="text/javascript"></script>
<script type="text/javascript">
 $(document).ready(function () {
	//$(function() {--修改这里
		$("#citylist").change(
				function() { //当城市选项改变时激活
					var selec = $(this).val(); //获取下拉城市选项改变的值city_id
					var url ="<%=basePath%>order/getAreaJsonDataByCityId";   //参数依次类型(action,Controller,area）            
                    //上面我们只用第一个参数action，也就是方法的请求地址
					$("#arealist").find("option").remove(); //清空
					//$("#arealist").val("请选择");//恢复默认
					//$.ajaxSetting.async = false;  //实现同步
					//$("#arealist2").find("option").remove(); //清空
					//$("#arealist2").val("请选择");//恢复默认
					/* getJSON语法
					   $.getJSON(
                        url,             //请求URL
                        [data],          //传参，可选参数
                        [callback]       //回调函数，可选参数,url与data成功后，执行此项
  　                                                     );        
					 */
					/*  如果下拉框值为1，则清空arealist,并且设置option值为1 */
					if(selec==-1)
					{
					    //如果选择的是请选择-对应的值是-1，则清空第二个下拉框，并添加请选择文字
						$("#arealist").find("option").remove(); //清空
						$("<option></option>").val("-1")
						.text("请选择").appendTo(
								$("#arealist"));
					}
					else
				    {
						//如果选择的是正确的城市对应的id数字，则查询对应的区县并赋值到第二个下拉框
						$.getJSON(url, {
							'city_id' : selec
						}, function(data) { //city_id是参数名，和Controllers中的action参数名相同
							//语法：$.each( collection, callback(indexInArray, valueOfElement) ) 
							$.each(data,
									function(i, item) {
										$("<option></option>").val(item["city_id"])
												.text(item["city_name"]).appendTo(
														$("#arealist"));
									}); //如果url访问成功  则执行function(data)这个函数（看仔细了`，这里该函数也是.getJSON的第三个参数）
						}); //function(data)获取了通过url返回来的值，并且循环读取出来            
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
						<h3>新增订单</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>order/createOrder" method="post">
							<table class="aTable">
								<tr>
                                    <%-- <input type="hidden"  name="manager_id" value="${manager_id}"/> --%>
                                    <input type="hidden"  name="manager_name" value="${manager_name}"/>
                                    
								</tr>
								<tr>
								<td class="order_ss"><span class="important">*</span>货物名称:</td>
								<td colspan="3">
								     <select id="goodslist" name="goods_name">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${goodsList}">
											<option value='<c:out value="${ul.goods_name }" />'>
												${ul.goods_name }</option>
										</c:forEach>
								     </select>
                                    </td>
                                    
							    </tr>
							    
								<tr><td class="order_ss">
								<span class="important">*</span>客户姓名:</td>
								<td colspan="3">
								     <select id="userlist" name="sender_user">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${cusinfo}">
											<option value='<c:out value="${ul.customer_name }" />'>
												${ul.customer_name }</option>
										</c:forEach>
								     </select></td>
                                </td>
							    </tr>
							    <tr>
								<td class="order_ss"><span class="important">*</span>收货人姓名:</td>
								<td colspan="3"><input type="text" name="receiver_user" style="width: 200px;" /></td>
							    </tr>
							    <tr>
								<td class="order_ss"><span class="important">*</span>收货人联系方式:</td>
								<td colspan="3"><input type="text" name="receiver_tel" style="width: 200px;" /></td>
							    </tr>
								<tr>
								<td class="order_ss"><span class="important">*</span>收货人所在地:</td>
								<td colspan="3">
								<select id="citylist" name="city_name">
								<option value="-1">请选择</option>
										<c:forEach var="ci" items="${citylist}">
											<option value='<c:out value="${ci.city_id }" />'>
												${ci.city_name }</option>
										</c:forEach>
								</select> 
								<select id="arealist" name="area_name">
								<option value="-1">请选择</option>
								         <%-- <c:forEach var="ci" items="${arealist}">
											<option value='<c:out value="${ci.city_name }" />'>
												${ci.city_name }</option>
										</c:forEach>  --%>
								</select></td>
							</tr>
							<tr>
								<td class="order_ss"><span class="important">*</span>详细地址:</td>
								<td colspan="3"><input type="text" name="receiver_address" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td class="order_ss"><span class="important">*</span>邮编:</td>
								<td colspan="3"><input type="text" name="receiver_post" style="width: 200px;" /></td>
							</tr>
							<tr><td class="order_ss">
								<span class="important">*</span>揽件员:</td>
								<td colspan="3">
								     <select id="receiver_employee" name="receiver_employee">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${workerreceiver}">
											<option value='<c:out value="${ul.worker_name }" />'>
												${ul.worker_name }</option>
										</c:forEach>
								     </select></td>
                                </td>
							</tr>
							<tr><td class="order_ss">
								<span class="important">*</span>派送员:</td>
								<td colspan="3">
								     <select id="sender_employee" name="sender_employee">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${workersender}">
											<option value='<c:out value="${ul.worker_name }" />'>
												${ul.worker_name }</option>
										</c:forEach>
								     </select></td>
                                </td>
							</tr>
							<tr><td class="order_ss">
								<span class="important">*</span>订单状态:</td>
								<td colspan="3">
								     <select id="order_pay" name="order_pay">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${orderpay}">
											<option value='<c:out value="${ul.pay_status }" />'>
												${ul.pay_status }</option>
										</c:forEach>
								     </select></td>
                                </td>
							</tr>
							<tr>
							
							<td  class="order_money">总金额：</td><td class="order_td"><input type="text" name="order_money" style="width: 200px;" /> </td></tr>
							<tr class="sSubmit">
							<td class="order_ss"></td>
								<td Colspan="4"><input type="submit" class="from_sub"
									value="提交" /></td>

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
