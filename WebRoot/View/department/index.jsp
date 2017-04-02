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
<title>机构管理主页</title>

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
							<h3>温馨提示</h3>
						</div>
						<div class="innerContent">
						<!-- 机构维护 -->
							<div class="" style="height="350px",width=200px">
							<p>您可以在此处管理机构！</p>
							</div>
                            <!-- start公司信息编辑 -->
                             <p>
								您可以在这里添加分拣中心、管理分拣中心<br /> <br />
								每个管理帐户的权限也是不一样的，指定权限的管理帐户可以做对应权限的操作。 <br /> <br /> <strong>注意：</strong>
								如您不知道该如何操作，您需要学习一下<a href="javascript:viod(0)">网站管理手册。</a>
					         </p>
							<!-- end 公司信息编辑 -->
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
