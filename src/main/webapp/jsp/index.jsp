<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的主页</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/metroStyle.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>
</head>
<body class="easyui-layout">
	<div id="north" data-options="region:'north'" style="height: 100px">1111</div>
	<div id="west" data-options="region:'west'" title="菜单"
		style="width: 200px; padding: 10px">
		<ul id="myTree" class="ztree"></ul>
	</div>
	<div id="center" data-options="region:'center'">
		<div id="tabs" class="easyui-tabs"
			data-options="fit:'true',border:false">
		      <div title='首页'>欢迎</div>	
		</div>
	</div>
</body>
</html>