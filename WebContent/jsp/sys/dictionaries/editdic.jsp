<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">字典-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="recid">字典ID</label>
					<input type="text" class="form-control" id="recid" name="recid" value="${dic.recid}">
				</div>
				<div class="form-group">
					<label for="dicType">字典类型</label>
					<input type="text" class="form-control" id="dicType" name="dicType" value="${dic.dicType}" required>
				</div>
				<div class="form-group">
					<label for="dicName">字典名称</label>
					<input type="text" class="form-control" id="dicName" name="dicName" value="${dic.dicName}" required>
				</div>
				<div class="form-group">
					<label for="note">备注</label>
					<input type="text" class="form-control" id="note" name="note" value="${dic.note}">
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
	var edit_nodeId = '${nodeId}';
	$(function(){
		//初始化界面
		if('add'==edit_handle){
			$('#addEditTitle').html("新增");
		}else if("edit"==edit_handle){
			$('#addEditTitle').html("修改");
			$('#dicType').attr("readonly","readonly");
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
			url:basepath+'sys/dictionaries/editDic',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功"
						});
					treeReload();
					$('#dicEdit').modal('hide');
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