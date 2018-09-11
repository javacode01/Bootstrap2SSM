<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">${comment}-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<#list columns as column>
				<#if column.propertyName!="creater"&&column.propertyName!="createTime"&&column.propertyName!="updater"&&column.propertyName!="updateTime">
				<#if column.propertyName!="recid">
				<div class="form-group">
				<#else>
				<div class="form-group" style="display:none;">
				</#if>
					<label for="${column.propertyName}">${column.columnComment}</label>
					<input type="text" class="form-control" id="${column.propertyName}" name="${column.propertyName}" value="${r"${"}${className?lower_case}.${column.propertyName}${r"}"}" required>
				</div>
				</#if>
				</#list>
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
	var edit_handle = '${r"${handle}"}';//操作类型
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
			url:basepath+'${urlPrefix}/${className?lower_case}/edit${className}',
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
