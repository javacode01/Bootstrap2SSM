<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">字典项-<small id="addEditItemTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditItemForm">
				<div class="form-group" style="display:none;">
					<label for="itemrecid">字典项ID</label>
					<input type="text" class="form-control" id="itemrecid" name="recid" value="${item.recid}">
				</div>
				<div class="form-group">
					<label for="itemdicType">字典类型</label>
					<input type="text" class="form-control" id="itemdicType" name="dicType" value="${item.dicType}" required readonly>
				</div>
				<div class="form-group">
					<label for="itemCode">字典项编码</label>
					<input type="text" class="form-control" id="itemCode" name="itemCode" value="${item.itemCode}" required>
				</div>
				<div class="form-group">
					<label for="itemName">字典项名称</label>
					<input type="text" class="form-control" id="itemName" name="itemName" value="${item.itemName}" required>
				</div>
				<div class="form-group">
					<label for="seq">排序</label>
					<input type="text" class="form-control" id="seq" name="seq" value="${item.seq}">
				</div>
				<div class="form-group">
					<label for="itemnote">备注</label>
					<input type="text" class="form-control" id="itemnote" name="note" value="${item.note}">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="addEditItemSubmit()">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<script type="text/javascript">
	var edit_item_handle = '${handle}';//操作类型
	$(function(){
		//初始化界面
		if('add'==edit_item_handle){
			$('#addEditItemTitle').html("新增");
			var selected = $("#tree").treeview('getSelected');
			$('#itemdicType').val(selected[0].data.dicType);
		}else if("edit"==edit_item_handle){
			$('#addEditItemTitle').html("修改");
		}
	});
	//提交
	function addEditItemSubmit(){
		//校验表单
		if(!$('#addEditItemForm').valid()){
			return false;
		}
		//提交
		$.ajax({
			url:basepath+'sys/dictionariesitem/editDicItem',
			type:'post',
			data:$("#addEditItemForm").serialize(),
			success:function(result){
				if(result.code=='success'){
					bootbox.alert({ 
						  size: "small",
						  title: "提示框",
						  message: "保存成功"
						});
					$('#table').bootstrapTable('refresh');
					$('#dicItemEdit').modal('hide');
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