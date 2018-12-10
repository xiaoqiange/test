<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>my tree</title>
<%
    String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet"  
	href="<%=path%>/css/zTreeStyle.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery.ztree.core.min.js"></script>
</head>
<body>
	<h4>My Tree</h4>
	<div>
		<ul id="mytree" class="ztree"></ul>
	</div>
	<script type="text/javascript">
	   var setting = {
			   data : {
				   key : {
					   name:"rName"
				   },
				simpleData : {
					enable : true,
					idKey : "rid",
					idPKey : "superId",
				}
			}, 
			async : {
				enable : true, //开启异步加载
				url : "<%=path%>/login/ztree", //设置获取异步动态加载的数据
				dataType : "text",
				autoParam : [ "rid" ]
			}
		}
		var zNodes = [ {
			name : "pNode 01",
			open : true,
			children : [ {
				name : "pNode 11",
				children : [ {
					name : "leaf node 111",
					url : "http://www.baidu.com"
				}, {
					name : "leaf node 112",
					url : "http://www.sina.com.cn",
					target : "maintaskframe"
				}, {
					name : "leaf node 113"
				}, {
					name : "leaf node 114"
				} ]
			}, {
				name : "pNode 12",
				children : [ {
					name : "leaf node 121"
				}, {
					name : "leaf node 122"
				}, {
					name : "leaf node 123"
				}, {
					name : "leaf node 124"
				} ]
			}, {
				name : "pNode 13 - no child",
				isParent : true
			} ]
		}, {
			name : "pNode 02",
			children : [ {
				name : "pNode 21",
				open : true,
				children : [ {
					name : "leaf node 211"
				}, {
					name : "leaf node 212"
				}, {
					name : "leaf node 213"
				}, {
					name : "leaf node 214"
				} ]
			}, {
				name : "pNode 22",
				children : [ {
					name : "leaf node 221"
				}, {
					name : "leaf node 222"
				}, {
					name : "leaf node 223"
				}, {
					name : "leaf node 224"
				} ]
			}, {
				name : "pNode 23",
				children : [ {
					name : "leaf node 231"
				}, {
					name : "leaf node 232"
				}, {
					name : "leaf node 233"
				}, {
					name : "leaf node 234"
				} ]
			} ]
		}, {
			name : "pNode 3 - no child",
			isParent : true
		} ];
		$(document).ready(function() {
			zTreeObj = $.fn.zTree.init($("#mytree"), setting, null); //初始化zTree，三个参数一次分别是容器(zTree 的容器 className 别忘了设置为 "ztree")、参数配置、数据源
		});
	</script>
</body>
</html>