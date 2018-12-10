<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/icon.css">
<link type="text/css" rel="stylesheet" href="../css/pagination.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<style type="text/css">
.main_form {
	margin: 32px auto;
	width: 480px;
}

.input_container {
	margin-bottom: 32px;
}
</style>
</head>
<body>
	<div>
		<table id="dg"></table>
	</div>
	<form class="main_form">
		<div id="addUsers" class="easyui-dialog" title="添加用户信息"
			style="width: 600px; height: 400px; text-align: center"
			data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
			<div>
				<label for="name">用户名：</label> <input id="name"
					class="easyui-textbox" name="name" type="text"
					style="width: 40%; height: 52px">
			</div>
			<div class="input_container">
				<label for="password">密码：</label> <input id="password"
					class="easyui-textbox" name="password" type="password"
					style="width: 40%; height: 52px">
			</div>
			<div>
				<label for="sex">性别：</label> <input id="sexm" name="sex"
					type="radio" value="1" checked="checked">男 <input id="sexw"
					name="sex" type="radio" value="2">女
			</div>
			<input type="button" value="添 加 " onclick="addUserInfo()" />
		</div>
	</form>
	<script type="text/javascript">
		window.onload = function() {
			$("#dg").datagrid({
				url : "../user/userlist",
				title : "用户列表",
				rownumbers : true,
				toolbar : toolbar,
				pagination : true,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50 ],
				columns : [ [ {
					field : 'uid',
					title : '用户ID',
					width : '100px',
					align : 'center'
				}, {
					field : 'name',
					title : '姓名',
					width : '100px',
					align : 'center'
				}, {
					field : 'code',
					title : '编号',
					width : '100px',
					align : 'center'
				}, {
					field : 'phone',
					title : '联系方式',
					width : '100px',
					align : 'center'
				}, {
					field : 'sex',
					title : '性别',
					width : '100px',
					align : 'center'
				}, {
					field : 'age',
					title : '年龄',
					width : '100px',
					align : 'center'
				}, {
					field : 'bir',
					title : '出生年月',
					align : 'center',
				} ] ]
			});
		}
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				addCustomer();
			}
		}, {
			text : '删除',
			iconCls : 'icon-cut',
		}, {
			text : '保存',
			iconCls : 'icon-save',
		} ];
		function addCustomer() {
			$("#name").val("");
			$("#password").val("");
			$("#addUsers").dialog('open');
		}
	</script>
</body>
</html>