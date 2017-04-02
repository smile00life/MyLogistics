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
<title>包裹柱状图</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>

</head>
<body>
<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
		<div class="content">
		  <div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
                  <div id="container" style="width: 550px; height: 400px; margin: 0 auto">
                 </div>
            </div>
            
          </div>
          <jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
          <!-- leftbox -->
        </div>
    </div>
 <jsp:include page="../shared/manager_footer.jsp"></jsp:include>
<script language="JavaScript">
$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '包裹成交量'   
   };
   var subtitle = {
      text: 'Package volume'  
   };
   var xAxis = {
      categories: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '成交量 (件)'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} 件</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
   var series= [{
        name: '总件数',
            data: [219.9, 221.5, 206.4, 229.2, 244.0, 276.0, 235.6, 248.5, 236.4, 294.1, 395.6, 224.4]
        }, {
            name: '一级分拣中心',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 123.5, 156.6, 92.3]
        }, {
            name: '二级分拣中心',
            data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 72.4, 85.2, 139.3, 51.2]
        }, {
            name: '三级分拣中心',
            data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 87.6, 69.1, 116.8, 51.1]
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   json.subtitle = subtitle; 
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#container').highcharts(json);
  
});
</script>
</body>
</html>
