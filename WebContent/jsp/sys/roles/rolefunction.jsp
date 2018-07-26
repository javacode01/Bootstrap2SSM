<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">权限分配</h4>
		</div>
		<div class="modal-body" id="rolefuction" style="overflow: auto;">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" id="addEditButton" onclick="addEditSubmit()">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<script type="text/javascript">
	var roleCode = "${roleCode}";
	$("#rolefuction").height($(window).height()-150);
	var zTreeObj;
   	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   	var setting = {
		async: {
			enable: true,
			autoParam: ["id"],
			url: basepath+"sys/functions/getFunctionNodesZTree?"+$("meta[name='_csrf_header']").attr("content")+"="+$("meta[name='_csrf']").attr("content"),
			type: "post",
			dataType:'json',
			otherParam: {"roleCode":roleCode}
		},
		callback: {
			onAsyncSuccess: function(event, treeId, treeNode, msg){
				//展开子节点
				var children = treeNode.children;
				$.each(treeNode.children,function(index,node){
					zTreeObj.reAsyncChildNodes(node,"refresh");
				});
			}
		},
		check:{
			enable:true
		},
		data:{
            simpleData:{
                enable: true,
                idKey:'id',
                pIdKey:'pid',
                rootPId: 0
            }
        },
        edit: {
    		enable: false
    	},
		view:{
			showLine: false,
			nameIsHTML:true,
			showIcon: false,
			showTitle:false
		}
   	};
   	// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   	var zNodes = [{id:"root",name:"&nbsp;<span class='fa fa-sitemap' style='font: normal normal normal 14px/1 FontAwesome;'></span>&nbsp;功能",isParent:true,data:{seq:"1"}}];
	if(roleCode==""){
		PluginUtil.info("参数错误");
		$('#roleEdit').modal('hide');
	}
	$(function(){
		//初始化界面
		initTree();
		//展开第一个节点
		zTreeObj.reAsyncChildNodes(zTreeObj.getNodes()[0],"refresh");
	});
	//提交
	function addEditSubmit(){
		var checkeds = zTreeObj.getCheckedNodes(true);
		var functionCodes = new Array();
		$.each(checkeds,function(index,node){
			functionCodes.push(node.data.functionCode);
		});
		PluginUtil.mask("roleEdit");
		//提交
		$.ajax({
			url:basepath+'sys/roles/saveRoleFunction',
			type:'post',
			data:{roleCode:roleCode,functionCodes:functionCodes.join(",")},
			success:function(result){
				PluginUtil.unmask("roleEdit");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
					$('#roleEdit').modal('hide');
				}else{
					PluginUtil.alert(result.data);
				}
			},
			error:function(error){
				PluginUtil.alert(error);
			}
		});
	}
	/**
	 * 初始化树结构
	 * @returns
	 */
	function initTree(){
		zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
	}
</script>