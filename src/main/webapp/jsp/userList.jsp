<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/easyui.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/icon.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/operationIcon.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/jeasyui.extensions.css">
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/pagination.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/jeasyui.extensions.js"></script>
<script type="text/javascript" src="<%=path%>/js/jeasyui.extensions.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/userList.js"></script>
<title>Insert title here</title>
<style type="text/css">
.main_form {
	margin: 32px auto;
	width: 400px;
}

.input_container {
	margin-bottom: 32px;
}
</style>
</head>
<body>
	<div class="easyui-panel" fit="true">
		<table id="dg"></table>
	</div>
	<form class="main_form">
		<div id="addUsers" class="easyui-dialog"
			style="width: 600px; height: 400px; padding: 10px 60px; text-align: center"
			data-options="iconCls:'icon-man',resizable:true,modal:true,closed:true,maximizable:true">
			<div class="input_container">
				<input id="name" label="用户名"
					data-options="prompt:'请输入用户名',iconCls:'icon-man'"
					class="easyui-textbox" name="name" type="text"
					style="width: 100%; height: 32px">
			</div>
			<div class="input_container">
				<input id="birthday" class="easyui-datebox" name="birthday"
					label="生日" style="width: 100%; height: 32px">
			</div>
			<div class="input_container">
			    <span>ICON图标：</span>
				<select id="iconCls" name="iconCls" class="easyui-comboicons"
					data-options="width: 260, autoShowPanel: false, multiple: false, size: '16'"></select>
			</div>
			<div class="input_container">
				<input id="sexm" name="sex" type="radio" value="1" checked="checked">男
				<input id="sexw" name="sex" type="radio" value="2">女
			</div>
			<input id="confirm" class="easyui-linkbutton" type="button"
				style="width: 30%; height: 32px" /> <input id="countermand"
				class="easyui-linkbutton" type="button" value="取消"
				style="width: 30%; height: 32px" />
		</div>
	</form>
</body>
</html>