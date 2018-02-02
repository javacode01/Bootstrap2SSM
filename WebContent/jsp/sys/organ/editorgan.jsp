<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">机构-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="organId">机构ID</label>
					<input type="text" class="form-control" id="organId" name="organId" value="${organ.organId}">
				</div>
				<div class="form-group" style="display:none;">
					<label for="parentCode">上级编码</label>
					<input type="text" class="form-control" id="parentCode" name="parentCode" value="${organ.parentCode}">
				</div>
				<div class="form-group">
					<label for="organCode">机构编码</label>
					<input type="text" class="form-control" id="organCode" name="organCode" value="${organ.organCode}" required>
				</div>
				<div class="form-group">
					<label for="organName">机构名称</label>
					<input type="text" class="form-control" id="organName" name="organName" value="${organ.organName}" required>
				</div>
				<div class="form-group">
					<label for="organLevel">机构级别</label>
					<select class="form-control" id="organLevel" name="organLevel" required></select>
				</div>
				<div class="form-group">
					<label for="organType">机构类型</label>
					<select class="form-control" id="organType" name="organType"></select>
				</div>
				<div class="form-group">
					<label for="iconUrl">机构图标</label>
					<input type="text" class="form-control" id="iconUrl" name="iconUrl" value="${organ.iconUrl}">
				</div>
				<div class="form-group">
					<label for="seq">序号</label>
					<input type="text" class="form-control" id="seq" name="seq" value="${organ.seq}">
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
		SysUtil.initSelect('organLevel',ORGANLEVEL,"${organ.organLevel}");
		SysUtil.initSelect('organType',ORGANTYPE,"${organ.organType}");
		//初始化界面
		if("add"==edit_handle){
			var pcode = '${parentOrgan.organCode}';
			$("#parentCode").val(pcode);
		}else if("edit"==edit_handle){
			$("#organCode").attr("readonly","readonly");
		}
	});
	function addEditSubmit(){
		if(!$('#addEditForm').valid()){
			return false;
		}
		//提交
		$.ajax({
			url:basepath+'sys/organ/editOrgan',
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
					$('#organModal').modal('hide');
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
</script>