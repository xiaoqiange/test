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
<link type="text/css" rel="stylesheet"
	href="<%=path%>/css/pagination.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<style type="text/css">
.input-container {
	margin-bottom: 28px;
}

.main_form {
	margin: 32px auto;
	width: 400px;
}

.form-wh {
	width: 100%;
	height: 52px;
}
</style>
</head>
<body>
	<form class="main_form" id="myForm">
		<div class="easyui-panel" title="用户登录" style="padding: 30px 60px;">
			<div class="input-container">
				<input type="text" class="easyui-textbox" name="name"
					labelPosition="top" id="name" label="用户名"
					data-options="iconCls:'icon-man'" style="width: 100%; height: 60px">
			</div>
			<div class="input-container">
				<input type="password" class="easyui-textbox" name="password"
					labelPosition="top" id="password" label="密码"
					data-options="iconCls:'icon-lock'"
					style="width: 100%; height: 60px">
			</div>
			<div>
				<input id="form-button" type="button" class="easyui-linkbutton"
					value="登录" style="width: 100%; height: 32px">
			</div>
		</div>
	</form>


	<script type="text/javascript">
	   $("#myForm").keyup(function(event){
		   if(event.keyCode==13){
			   var url="<%=path%>/login/login";
			   var name=$("#name").textbox('getValue');
			   var password=$("#password").textbox('getValue');
			   $.post(url,{name:name,password:password},function(result){
				   location.href="<%=path%>/user/index";
			   });
			}
		});
	</script>
</body>
</html>