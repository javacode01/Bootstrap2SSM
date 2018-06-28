/**
 * 初始化的方法
 * 
 * @returns
 */
function init() {
	initTable();
}

/**
 * 初始化表格
 * @returns
 */
function initTable(){
	$('#rolesTable').bootstrapTable({
		url:basepath+"sys/roles/listRolesByPage",
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        queryParams: function(params){
        	return getParam(params);
        },									//传递参数（*）
        pagination:true,//启用分页
        sidePagination:"server",//在服务端分页
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        pageNumber:1,//首页页码
        pageSize:10,//页面数据条数
        pageList:[10,25,50,100],//页面条数选择
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "roleId",                     //每一行的唯一标识，一般为主键列
        toolbar: "#toolbar",                //工具按钮用哪个容器
        showColumns:true,
        showRefresh:true,
        showToggle:true,
        columns: [{
        	checkbox:true
        }, {
            field: 'roleCode',
            title: '角色编码',
            width:"20%"
        }, {
            field: 'roleName',
            title: '角色名',
            width:"20%"
        }, {
            field: 'description',
            title: '角色描述',
            width:"50%"
        }]
	});
}

/**
 * 获取查询条件
 * @param params
 * @returns
 */
function getParam(params){
	var filter = new HashMap();
	var searchRoleName = $("#search_roleName").val();
	if(searchRoleName){
		filter.put("roleName@LIKE","%"+searchRoleName+"%");
	}
	var searchRoleCode = $("#search_roleCode").val();
	if(searchRoleCode){
		filter.put("roleCode@LIKE","%"+searchRoleCode+"%");
	}
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 查询
 * @returns
 */
function search(){
	$('#rolesTable').bootstrapTable('destroy');
	initTable();
}

/**
 * 新增角色
 * @returns
 */
function addRole(){
	$("#roleEdit").load(basepath+"sys/roles/toEditRole?handle=add",function(){
		$("#roleEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改角色
 * @returns
 */
function editRole(){
	var selected = $("#rolesTable").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要编辑的角色"
			});
		return false;
	}
	$("#roleEdit").load(basepath+"sys/roles/toEditRole?handle=edit&roleId="+selected[0].roleId,function(){
		$("#roleEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典删除
 * @returns
 */
function removeRoles(){
	var selected = $("#rolesTable").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要删除的角色"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的角色？",
	    buttons: {
	        confirm: {
	            label: '确定',
	            className: 'btn-danger'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-success'
	        }
	    },
	    callback: function (result) {
	       if(result){
	    	   var roleCodes = new Array();
	    	   $.each(selected,function(index,data){
	    		   roleCodes.push(data.roleCode);
	    	   });
	    	   $('body').busyLoad("show",{
	    		   fontawesome: "fa fa-spinner fa-spin fa-3x fa-fw"
	    	   });
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/roles/deleteRoles',
	    		   type:'post',
	    		   data:{roleCodes:roleCodes.join(",")},
	    		   success:function(result){
	    			   $('body').busyLoad("hide");
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "删除成功"
	    						});
	    				   search();
	    			   }else{
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "警告框",
	    						  message: result.data
	    						});
	    			   }
	    		  },
	    		  error:function(error){
	    			  $('body').busyLoad("hide");
	    			  bootbox.alert({ 
						  size: "small",
						  title: "警告框",
						  message: error
						});
	    		  }
	    	   });
	       }
	    }
	});
}

/**
 * 权限分配
 * @returns
 */
function roleFunction(){
	var selected = $("#rolesTable").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个角色分配权限"
			});
		return false;
	}
	$("#roleEdit").load(basepath+"sys/roles/toRoleFunction?roleCode="+selected[0].roleCode,function(){
		$("#roleEdit").modal({backdrop: 'static', keyboard: false});
	});
}