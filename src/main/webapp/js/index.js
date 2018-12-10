function addTabs(event, treeId, treeNode) {
	if (treeNode.url == "") {
		return;
	} else {
		addTab(treeNode.sname, treeNode.url);
	}
};
var setting = {
	data : {
		key : {
			name : 'sname',
			url : 'null'
		},
		simpleData : {
			enable : true,
			idKey : 'sid',
			idPKey : 'supersid'
		}
	},
	async : {
		enable : true,
		url : '../user/source',
		dataType : "text"
	},
	callback : {
		onClick : addTabs
	}
}
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#myTree"), setting, null); // 初始化zTree，三个参数一次分别是容器(zTree
																// 的容器 className
																// 别忘了设置为
																// "ztree")、参数配置、数据源
});
function addTab(title, url) {
	if ($('#tabs').tabs('exists', title)) {
		$('#tabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		$('#tabs').tabs('add', {
			tools : [ {
				iconCls : 'icon-mini-refresh',
				handler : function() {
					var tab = $('#tabs').tabs('getSelected'); // 获取选择的面板
					$('#tabs').tabs('update', {
						tab : tab,
						options : {
							content : content
						}
					});
				}
			} ],
			title : title,
			content : content,
			closable : true
		});
	}
}
(function($) {
	var buttonDir = {
		north : 'down',
		south : 'up',
		east : 'left',
		west : 'right'
	};
	$.extend($.fn.layout.paneldefaults, {
		onBeforeCollapse : function() {
			/**/
			var popts = $(this).panel('options');
			var dir = popts.region;
			var btnDir = buttonDir[dir];
			if (!btnDir)
				return false;

			setTimeout(function() {
				var pDiv = $('.layout-button-' + btnDir).closest(
						'.layout-expand').css({
					textAlign : 'center',
					lineHeight : '18px',
					fontWeight : 'bold'
				});

				if (popts.title) {
					var vTitle = popts.title;
					if (dir == "east" || dir == "west") {
						var vTitle = popts.title.split('').join('<br/>');
						pDiv.find('.panel-body').html(vTitle);
					} else {
						$('.layout-button-' + btnDir).closest('.layout-expand')
								.find('.panel-title').css({
									textAlign : 'left'
								}).html(vTitle)
					}

				}
			}, 100);

		}
	});
})(jQuery);