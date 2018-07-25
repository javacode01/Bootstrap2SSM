<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sys.utils.BspUtils"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">设置组织结构的规则-<small id="addEditTitle">新增</small></h4>
		</div>
		<div class="modal-body">
			<form role="form" id="addEditForm">
				<div class="form-group" style="display:none;">
					<label for="recid">主键</label>
					<input type="text" class="form-control" id="recid" name="recid" value="${sysorgandesign.recid}">
				</div>
				<div class="form-group">
					<label for="organLevel">组织级别</label>
					<input type="text" class="form-control" id="organLevel" name="organLevel" value="${sysorgandesign.organLevel}" required>
				</div>
				<div class="form-group">
					<label for="organLevelName">组织级别名称</label>
					<input type="text" class="form-control" id="organLevelName" name="organLevelName" value="${sysorgandesign.organLevelName}" required>
				</div>
				<div class="form-group">
					<label for="nextLevel">允许创建的下级级别</label>
					<select class="form-control" id="nextLevel" name="nextLevel" multiple="multiple" style="width:100%;"></select>
				</div>
				<div class="form-group">
					<label for="haveUser">是否可以创建用户</label>
					<select class="form-control" id="haveUser" name="haveUser" required></select>
				</div>
				<div class="form-group">
					<label for="iconUrl">默认图标<a href="http://www.fontawesome.com.cn/faicons/" target="view_window">【图标参考】</a></label>
					<input type="text" class="form-control" id="iconUrl" name="iconUrl" value="${sysorgandesign.iconUrl}" required>
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
		//初始化select标签
		SysUtil.initSelect('haveUser',ISORNOT,"${sysorgandesign.haveUser}");
		SysUtil.initSelect('nextLevel',<%=BspUtils.listOrganDesign()%>,"${sysorgandesign.nextLevel}");
		var nextlevel = "${sysorgandesign.nextLevel}";
		$("#nextLevel").select2({
			tags: true,
			placeholder: "请选择",
		});
		if(nextlevel!=""){
			$('#nextLevel').val(nextlevel.split(",")).trigger('change');
		}
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
			url:basepath+'sys/sysorgandesign/editSysOrganDesign',
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
