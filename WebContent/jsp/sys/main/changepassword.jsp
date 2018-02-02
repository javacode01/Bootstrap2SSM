<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">修改密码</h4>
		</div>
		<div class="modal-body">
			<form role="form" id="changePasswordForm">
				<div class="form-group">
					<label for="password">原密码</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				<div class="form-group">
					<label for="newPassword">新密码</label>
					<input type="password" class="form-control" id="newPassword" name="newPassword" required>
				</div>
				<div class="form-group">
					<label for="reNewPassword">确认新密码</label>
					<input type="password" class="form-control" id="reNewPassword" name="reNewPassword" required>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="changePasswordSubmit()">确认修改</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<script type="text/javascript">
	function changePasswordSubmit(){
		if(!$('#changePasswordForm').valid()){
			return false;
		}
		if($('#newPassword').val()!==$('#reNewPassword').val()){
			bootbox.alert({ 
				  size: "small",
				  title: "提示框",
				  message: "两次密码输入不一致"
				});
			return false;
		}
		//提交
		$.ajax({
			url:basepath+'sys/users/changePassword',
			type:'post',
			data:{
				password:toMD5Str($("#password").val()),
				newPassword:toMD5Str($("#newPassword").val())
			},
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "修改密码成功（将退出系统，需要重新登录）",
						  callback: function () {
							  logout();
						}});
					$('#mainModal').modal('hide');
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