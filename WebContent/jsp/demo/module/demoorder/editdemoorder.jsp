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
				<div class="form-group">
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
				<div class="form-group">
					<label for="creater">创建人</label>
					<input type="text" class="form-control" id="creater" name="creater" value="${demoorder.creater}" required>
				</div>
				<div class="form-group">
					<label for="createTime">创建时间</label>
					<input type="text" class="form-control" id="createTime" name="createTime" value="${demoorder.createTime}" required>
				</div>
				<div class="form-group">
					<label for="updater">更新人</label>
					<input type="text" class="form-control" id="updater" name="updater" value="${demoorder.updater}" required>
				</div>
				<div class="form-group">
					<label for="updateTime">更新时间</label>
					<input type="text" class="form-control" id="updateTime" name="updateTime" value="${demoorder.updateTime}" required>
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
		//提交
		$.ajax({
			url:basepath+'module/demoorder/editDemoOrder',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功"
						});
					search();
					$('#editModal').modal('hide');
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
