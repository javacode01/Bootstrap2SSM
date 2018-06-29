<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">角色-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="roleId">角色Id</label>
					<input type="text" class="form-control" id="roleId" name="roleId" value="${role.roleId}">
				</div>
				<div class="form-group">
					<label for="roleCode">角色编码</label>
					<input type="text" class="form-control" id="roleCode" name="roleCode" value="${role.roleCode}" required>
				</div>
				<div class="form-group">
					<label for="roleName">角色名</label>
					<input type="text" class="form-control" id="roleName" name="roleName" value="${role.roleName}" required>
				</div>
				<div class="form-group">
					<label for="description">角色描述</label>
					<input type="text" class="form-control" id="description" name="description" value="${role.description}">
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
	var edit_handle = '${handle}';//操作类型
	$(function(){
		//初始化界面
		if('add'==edit_handle){
			$('#addEditTitle').html("新增");
		}else if("edit"==edit_handle){
			$('#addEditTitle').html("修改");
			$('#roleCode').attr("readonly","readonly");
		}
	});
	//提交
	function addEditSubmit(){
		//校验表单
		if(!$('#addEditForm').valid()){
			return false;
		}
		PluginUtil.mask("roleEdit");
		//提交
		$.ajax({
			url:basepath+'sys/roles/editRole',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				PluginUtil.unmask("roleEdit");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
					search();
					$('#roleEdit').modal('hide');
				}else{
					PluginUtil.alert(result.data);
				}
			},
			error:function(error){
				PluginUtil.unmask("roleEdit");
				PluginUtil.alert(error);
			}
		});
	}
</script>