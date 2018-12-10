$(document).ready(function() {
		    $("#countermand").click(function(){
		    	$("#addUsers").dialog("close");
		    });
			$("#birthday").datebox({
				panelWidth:"380px",
// 				formatter:function(date){
// 					var y=date.getFullYear();
// 					var m=date.getMonth()+1;
// 					var d=date.getDate();
// 					return y+"年"+m+"月"+d+"日";
// 				}
			});
			$("#confirm").tooltip({
				position: 'bottom',
			    content: '<span style="color:#fff">This is the tooltip message.</span>',
			    onShow: function(){
			        $(this).tooltip('tip').css({
			            backgroundColor: '#666',
			            borderColor: '#666'
			        });
			    }
			});
			$("#dg").datagrid({
				url : "../user/userlist",
				rownumbers : true,
				striped : true,
				fit : true,
				fitColumns : true,
				singleSelect : true,
				toolbar : toolbar,
				pagination : true,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50 ],
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true,
				} ] ],
				columns : [ [ {
					field : 'uid',
					title : '用户ID',
					width : 100,
					align : 'center'
				}, {
					field : 'name',
					title : '姓名',
					width : 100,
					align : 'center'
				}, {
					field : 'code',
					title : '编号',
					width : 100,
					align : 'center'
				}, {
					field : 'phone',
					title : '联系方式',
					width : 100,
					align : 'center'
				}, {
					field : 'sex1',
					title : '性别',
					width : 100,
					align : 'center'
				}, {
					field : 'age',
					title : '年龄',
					width : 100,
					align : 'center'
				}, {
					field : 'bir',
					title : '出生年月',
					width : 100,
					align : 'center',
				} ] ]
			});
		});
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				addCustomer();
			}
		}, {
			text : '删除',
			iconCls : 'icon-remove',
		}, {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
			    editCustomer();
			}
		} ];
		function addCustomer() {
			$("#name").textbox("setValue","");
            $("#birthday").datebox("setValue","");
			$("#confirm").val("添加");
			$("#addUsers").dialog({
                title:"添加用户信息"
            });
			$("#addUsers").dialog('open');
		}
		function editCustomer() {
			var row=$("#dg").datagrid("getSelected");
			$("#name").textbox("setValue",row.name);
			$("#birthday").datebox("setValue",row.bir);
			$("#addUsers").dialog({
                title:"修改用户信息"
            });
			$("#confirm").val("保存");
			$("#addUsers").dialog('open');
		}