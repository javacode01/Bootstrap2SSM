<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">功能-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="functionId">功能ID</label>
					<input type="text" class="form-control" id="functionId" name="functionId" value="${function.functionId}">
				</div>
				<div class="form-group" style="display:none;">
					<label for="parentCode">上级编码</label>
					<input type="text" class="form-control" id="parentCode" name="parentCode" value="${function.parentCode}">
				</div>
				<div class="form-group">
					<label for="functionCode">功能编号</label>
					<input type="text" class="form-control" id="functionCode" name="functionCode" value="${function.functionCode}" required>
				</div>
				<div class="form-group">
					<label for="functionName">功能名称</label>
					<input type="text" class="form-control" id="functionName" name="functionName" value="${function.functionName}" required>
				</div>
				<div class="form-group">
					<label for="functionLevel">功能级别</label>
					<select class="form-control" id="functionLevel" name="functionLevel" onchange="changeLevel()" required></select>
				</div>
				<div class="form-group acitonfunction">
					<label for="functionType">功能类型</label>
					<select class="form-control" id="functionType" name="functionType"></select>
				</div>
				<div class="form-group acitonfunction">
					<label for="functionUrl">资源地址</label>
					<input type="text" class="form-control" id="functionUrl" name="functionUrl" value="${function.functionUrl}">
				</div>
				<div class="form-group acitonfunction">
					<label for="defaultAction">是否默认</label>
					<select class="form-control" id="defaultAction" name="defaultAction"></select>
				</div>
				<div class="form-group showfunction">
					<label for="functionIcon">功能图标</label>
					<input type="text" class="form-control" id="functionIcon" name="functionIcon" value="${function.functionIcon}">
				</div>
				<div class="form-group">
					<label for="seq">序号</label>
					<input type="text" class="form-control" id="seq" name="seq" value="${function.seq}" required>
				</div>
			</form>
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
	var edit_handle = '${handle}';
	$(function(){
		//初始化select标签
		SysUtil.initSelect('functionType',FTYPE,"${function.functionType}");
		SysUtil.initSelect('defaultAction',ISORNOT,"${function.defaultAction}");
		//初始化界面
		if("add"==edit_handle){
			var pcode = '${parentFunction.functionCode}';
			var plevel = '${parentFunction.functionLevel}';
			if(plevel=="1"){
				$("#functionLevel").append("<option value='1'>模块</option>");
				$("#functionLevel").append("<option value='2'>功能</option>");
			}else if(plevel=="2"){
				$("#functionLevel").append("<option value='3'>操作</option>");
			}
			$("#parentCode").val(pcode);
		}else if("edit"==edit_handle){
			SysUtil.initSelect('functionLevel',FLEVEL,"${function.functionLevel}");
			$("#functionCode").attr("readonly","readonly");
			$("#functionLevel").attr("disabled","disabled");
		}
		$("#functionLevel").change();
	});
	function addEditSubmit(){
		if(!$('#addEditForm').valid()){
			return false;
		}
		$("#functionLevel").removeAttr("disabled");
		//提交
		$.ajax({
			url:basepath+'sys/functions/editFunctions',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功"
						});
					$('#table').bootstrapTable('refresh');
					$('#myModal').modal('hide');
					var selected = $("#tree").treeview('getSelected');
					if("add"==edit_handle){
						$('#tree').treeview('collapseNode', [selected[0].nodeId, {silent: true}]);
						var nodeIds = new Array();
						$.each(selected[0].nodes,function(index,item){
							nodeIds.push(item.nodeId);
						});
						$.each(nodeIds,function(index,item){
							$("#tree").treeview("deleteNode", [item, { silent: true }]);
						});
						$('#tree').treeview('expandNode',[selected[0].nodeId, {levels: 1}]);
					}else if("edit"==edit_handle){
						var parentNode = $('#tree').treeview('getParent', selected[0].nodeId);
						$('#tree').treeview('collapseNode', [parentNode.nodeId, {silent: true}]);
						var nodeIds = new Array();
						$.each(parentNode.nodes,function(index,item){
							nodeIds.push(item.nodeId);
						});
						$.each(nodeIds,function(index,item){
							$("#tree").treeview("deleteNode", [item, { silent: true }]);
						});
						$('#tree').treeview('expandNode',[parentNode.nodeId, {levels: 1}]);
					}
				}else{
					bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: result.data
						});
				}
			},
			error:function(error){
				bootbox.alert({ 
					  size: "small",
					  title: "警告框",
					  message: error
					})
			}
		});
	}
	function changeLevel(){
		var level = $("#functionLevel option:selected").val();
		if("1"==level||"2"==level){
			$(".acitonfunction").hide();
			$(".showfunction").show();
		}else if("3"==level){
			$(".acitonfunction").show();
			$(".showfunction").hide();
		}
	}
</script>