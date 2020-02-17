<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title">分配角色</h4>
		</div>
		<div class="modal-body" id="userrole" style="overflow: auto;">
			<table id="roleTable"></table>
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
	var userCode = "${userCode}";
	$("#userrole").height($(window).height()-150);
	if(userCode==""){
		PluginUtil.info("参数错误");
		$('#userModal').modal('hide');
	}
	$(function(){
		initRoleTable();
	});
	function initRoleTable(){
		$('#roleTable').bootstrapTable({
			url:basepath+"sys/roles/listRoles",
	        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	        queryParams: function(params){
	        	return getParam1(params);
	        },									//传递参数（*）
	        clickToSelect: true,                //是否启用点击选中行
	        uniqueId: "roleId",                     //每一行的唯一标识，一般为主键列
	        onLoadSuccess:function(data){
	        	initSelected(data);
	        },
	        columns: [{
	        	checkbox:true
	        }, {
	            field: 'roleCode',
	            title: '角色编码',
	            width:'25%'
	        }, {
	            field: 'roleName',
	            title: '角色名',
	            width:'25%'
	        }, {
	            field: 'description',
	            title: '角色描述',
	            width:'40%'
	        }]
		});
	}
	//获取查询条件
	function getParam1(params){
		var filter = new HashMap();
		$.extend(params,{filter:filter.getJSON()});
		return params;
	}
	//获取已分配权限
	function initSelected(rows){
		$.ajax({
			url:basepath+'sys/users/listUserRole',
			type:'post',
			data:{userCode:userCode},
			success:function(result){
				if(result.code=='success'){
					var list = result.data;
					$.each(rows,function(index,row){
						$.each(list,function(index1,data){
							if(row.roleCode==data.roleCode){
								$('#roleTable').bootstrapTable('check',index);
								return false;
							}
						});
					});
				}
			}
		});
	}
	//提交
	function addEditSubmit(){
		var selected = $("#roleTable").bootstrapTable("getSelections");
		var roleCodes = new Array();
		$.each(selected,function(index,item){
			roleCodes.push(item.roleCode);
		});
		PluginUtil.mask("userModal");
		//提交
		$.ajax({
			url:basepath+'sys/users/saveUserRole',
			type:'post',
			data:{userCode:userCode,roleCodes:roleCodes.join(",")},
			success:function(result){
				PluginUtil.unmask("userModal");
				if(result.code=='success'){
					PluginUtil.info("保存成功");
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