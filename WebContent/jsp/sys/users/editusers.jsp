<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">用户-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="userId">用户ID</label>
					<input type="text" class="form-control" id="userId" name="userId" value="${user.userId}">
				</div>
				<div class="form-group" style="display:none;">
					<label for="organCode">组织机构</label>
					<input type="text" class="form-control" id="organCode" name="organCode" value="${user.organCode}">
				</div>
				<div class="form-group">
					<label for="userCode">用户编码</label>
					<input type="text" class="form-control" id="userCode" name="userCode" value="${user.userCode}" required>
				</div>
				<div class="form-group">
					<label for="userName">用户名称</label>
					<input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" required>
				</div>
				<div class="form-group">
					<label for="status">状态</label>
					<select class="form-control" id="status" name="status" required></select>
				</div>
				<div class="form-group">
					<label for="system">是否系统用户</label>
					<select class="form-control" id="system" name="system"></select>
				</div>
				<div class="form-group">
					<label for="nickname">昵称</label>
					<input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}">
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
		SysUtil.initSelect('status',USERSSTATUS,"${user.status}");
		SysUtil.initSelect('system',USERSSYSTEM,"${user.system}");
		//初始化界面
		if("add"==edit_handle){
			var organCode = '${organCode}';
			$("#organCode").val(organCode);
			$('#addEditTitle').html("新增");
		}else if("edit"==edit_handle){
			$('#addEditTitle').html("修改");
			$('#userCode').attr("readonly","readonly");
		}
	});
	function addEditSubmit(){
		if(!$('#addEditForm').valid()){
			return false;
		}
		PluginUtil.mask("userModal");
		//提交
		$.ajax({
			url:basepath+'sys/users/editUsers',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				PluginUtil.unmask("userModal");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
					$('#table').bootstrapTable('refresh');
					$('#userModal').modal('hide');
				}else{
					PluginUtil.alert(result.data);
				}
			},
			error:function(error){
				PluginUtil.unmask("userModal");
				PluginUtil.alert(error);
			}
		});
	}
</script>