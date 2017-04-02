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
<title>Title</title>
<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/jquery.validate.vsdoc.js"
	type="text/javascript"></script>
<script src="<%=basePath%>content/js/citymenu.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
 $(document).ready(function () {
	//$(function() {--修改这里
		$("#citylist").change(
				function() { //当城市选项改变时激活
					var selec = $(this).val(); //获取下拉城市选项改变的值city_id
					var url ="<%=basePath%>city/getAreaJsonDataByCityId";   //参数依次类型(action,Controller,area）            
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
					
				
				
		$("#arealist").change(
				function() { //当城市选项改变时激活
					var selec = $(this).val(); //获取下拉城市选项改变的值
					var url ="<%=basePath%>city/getAreaJsonDataByCityId";   //参数依次类型(action,Controller,area）            
                    //上面我们只用第一个参数action，也就是方法的请求地址
                    //$("#arealist2").find("option").remove(); //清空
					
					/* getJSON语法
					   $.getJSON(
                        url,             //请求URL
                        [data],          //传参，可选参数
                        [callback]       //回调函数，可选参数,url与data成功后，执行此项
  　                                                     );        
					 */
					 if(selec==-1)
						{
						    //如果选择的是请选择-对应的值是-1，则清空第二个下拉框，并添加请选择文字
							//$("#arealist2").find("option").remove(); //清空
							$("<option></option>").val("-1")
							.text("请选择").appendTo(
									$("#arealist2"));
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
															$("#arealist2"));
										}); //如果url访问成功  则执行function(data)这个函数（看仔细了`，这里该函数也是.getJSON的第三个参数）
							}); //function(data)获取了通过url返回来的值，并且循环读取出来            
					    }
	           });  
		
	});
</script>
<body>
	<select id="citylist" name="citylist">
	<option  value="-1">请选择
				</option>
		<c:forEach var="ci" items="${citylist}">
		
			<option value='<c:out value="${ci.city_id }" />'>
				${ci.city_name }</option>
		</c:forEach>
	</select>
	<select id="arealist" name="arealist">
	<option value="-1">请选择
				</option>
	    <%-- <c:forEach var="ci" items="${arealist}">
			<option value='<c:out value="${ci.city_name }" />'>
				${ci.city_name }</option>
		</c:forEach> --%>
	</select>
	 <select id="arealist2" name="arealist2">
	 
	    <option  value="-1">请选择
				</option>
	    <%-- <c:forEach var="ci" items="${arealist3}">
			<option value='<c:out value="${ci.city_id }" />'>
				${ci.city_name }</option>
		</c:forEach> --%>
	</select> 
</body>
</html>
