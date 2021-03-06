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
					<label for="iconUrl">机构图标<a href="http://www.fontawesome.com.cn/faicons/" target="view_window">【图标参考】</a></label>
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
		//初始化界面
		if("add"==edit_handle){
			//初始化select标签
			SysUtil.initSelect('organLevel',nextLevel("${parentOrgan.organLevel}",ORGANLEVEL),"");
			var pcode = '${parentOrgan.organCode}';
			$("#parentCode").val(pcode);
		}else if("edit"==edit_handle){
			//初始化select标签
			SysUtil.initSelect('organLevel',ORGANLEVEL,"${organ.organLevel}");
			$("#organCode").attr("readonly","readonly");
			$("#organLevel").attr("disabled","disabled");
		}
	});
	$("#organLevel").change(function(){
		var items = ORGANLEVEL;
		for(var i=0;i<items.length;i++){
			if(items[i].itemCode==$("#organLevel").val()){
				$('#iconUrl').val(items[i].iconUrl)
			}
		}
	});
	function addEditSubmit(){
		if(!$('#addEditForm').valid()){
			return false;
		}
		PluginUtil.mask("organModal");
		//提交
		$.ajax({
			url:basepath+'sys/organ/editOrgan',
			type:'post',
			data:$("#addEditForm").serialize(),
			success:function(result){
				PluginUtil.unmask("organModal");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
					$('#table').bootstrapTable('refresh');
					$('#organModal').modal('hide');
					var selected = zTreeObj.getSelectedNodes();
					if("add"==edit_handle){
						selected[0].isParent=true
						zTreeObj.updateNode(selected[0]);
						zTreeObj.reAsyncChildNodes(selected[0],"refresh");
					}else if("edit"==edit_handle){
						selected[0].name = "&nbsp;<span class='"+$('#iconUrl').val()+"' style='font: normal normal normal 14px/1 FontAwesome;'></span>&nbsp;"+$('#organName').val();
						zTreeObj.updateNode(selected[0]);
						showDetail("","",selected[0]);
					}
				}else{
					PluginUtil.alert(result.data);
				}
			},
			error:function(error){
				PluginUtil.unmask("organModal");
				PluginUtil.alert(error);
			}
		});
	}
</script>