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
<title>订单查询界面</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<link href="<%=basePath%>content/bootstrap/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
	<!-- center开始 -->
	<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<h3>订单详情</h3>
					</div>
					<div class="innerContent">
					    <table>
					       <tr>
							
						   <td class="order_ss">订单号：</td><td  style="width: 200px;">${orderinfo.order_code }</td>
						   <td class="order_ss">商品条形码：</td><td  style="width: 200px;height:100px;"><img src="<%=basePath%>${orderinfo.img_address }"/></td>
							</tr>
						</table>
							<hr />
						<table>
							
							
							<tr>
							<td class="order_ss">发件人姓名：</td><td class="order_td">${orderinfo.sender_user }</td>
							
							<!-- 公司地址、邮编、名称、联系方式 -->
							<td class="order_ss">邮编：</td><td class="order_td">${sysinfo.company_code }</td>
							</tr>
							<tr>
							<td class="order_ss">发件地址：</td><td class="order_td" style="width: 200px;">${sysinfo.company_address }</td></tr>
							<tr>
							<td class="order_ss">单位名称：</td><td class="order_td">${sysinfo.company_name }</td></tr>
							<tr>
							<td class="order_ss">联系方式：</td><td class="order_td" >${sysinfo.company_tel }</td></tr>
						</table>
						   <hr />
						<table>
							
							<tr>
							<td class="order_ss">收件人姓名：</td><td class="order_td">${orderinfo.receiver_user }</td>
							<td class="order_ss">邮编：</td><td class="order_td">${orderinfo.receiver_post }</td></tr>
							<tr>
							<td class="order_ss">收货人详细地址：</td><td class="order_td" style="width: 200px;" >${orderinfo.receiver_address } </td></tr>
							<tr>
							<td class="order_ss">收货人联系方式：</td><td class="order_td">${orderinfo.receiver_tel }</td></tr>
						</table>
							
							<hr />
							<table>
							<tr>
							<td class="order_ss">商品名称：</td><td class="order_td" style="width: 200px;">${orderinfo.goods_name }</td>
							</tr>
							<td class="order_ss">商品内容：</td><td <td class="order_td">${goods_info.goods_content }</td></tr>
							<tr>
							<td class="order_ss">商品重量：</td><td class="order_td" style="width: 200px;">${goods_info.goods_weight }</td>
							<td class="order_ss">商品类型：</td><td class="order_td">${goods_info.goods_type }</td>
							
							
							</tr>
							<tr>
							<td class="order_ss">起送价：</td><td class="order_td">${costInfo.cost_price }</td>
							<td class="order_ss">每公斤加价：</td><td class="order_td">${costInfo.cost_per }</td>
							</tr>
						</table>
						<hr />
						<table>
							<tr>
							<td class="order_ss">揽件员：</td><td <td class="order_td">${orderinfo.receiver_employee } </td>
							<td class="order_ss">派送员：</td><td <td class="order_td">${orderinfo.sender_employee }</td></tr>
							<tr>
							<td class="order_ss">下单时间：</td><td class="order_td" style="width: 200px;">${orderinfo.create_time }</td></tr>
							
							
							<tr>
							<td class="order_ss">订单状态：</td><td class="order_td">${orderinfo.order_pay }</td>
							</tr>
						</table>
						<hr/>
						<table>
						
							<tr>
							<td class="order_ss"></td><td style="width: 100px;"></td>
							<td  class="order_money">总金额：</td><td class="order_td" style="font-size:20px;text-align:center;">￥${orderinfo.order_money } </td></tr>
						</table>
						<!-- End of  #contentlist -->
					</div>
				</div>
				<!-- End of  #contentlist -->
			</div>
			<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
		</div>
	
	
	  <!-- center结束 -->
	</div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
	</div>
	</body>
</html>
