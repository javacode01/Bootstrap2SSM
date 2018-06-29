<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">订单（示例表）-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="recid">订单id</label>
					<input type="text" class="form-control" id="recid" name="recid" value="${demoorder.recid}" required>
				</div>
				<div class="form-group">
					<label for="orderNo">订单编号</label>
					<input type="text" class="form-control" id="orderNo" name="orderNo" value="${demoorder.orderNo}" required>
				</div>
				<div class="form-group">
					<label for="description">订单简介</label>
					<input type="text" class="form-control" id="description" name="description" value="${demoorder.description}" required>
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
		}
	});
	//提交
	function addEditSubmit(){
		//校验表单
		if(!$('#addEditForm').valid()){
			return false;
		}
		PluginUtil.mask("editModal");
		//提交
		$.ajax({
			url:basepath+'module/demoorder/editDemoOrder',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				PluginUtil.unmask("editModal");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
					search();
					$('#editModal').modal('hide');
				}else{
					PluginUtil.alert(result.data);
				}
			},
			error:function(error){
				PluginUtil.unmask("editModal");
				PlubinUtil.alert(error);
			}
		});
	}
</script>
