/**
 * 初始化的方法
 * 
 * @returns
 */
function init() {
	initTree();
	initTable();
}

/**
 * 初始化树结构
 * @returns
 */
function initTree(){
	zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
}

/**
 * 初始化表格
 * @returns
 */
function initTable(){
	$('#table').bootstrapTable({
		url:basepath+"sys/users/listUsersByPage",
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
        uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
        toolbar: "#toolbar",                //工具按钮用哪个容器
        showColumns:true,
        showRefresh:true,
        showToggle:true,
        columns: [{
        	checkbox:true
        }, {
            field: 'userCode',
            title: '用户编码'
        }, {
            field: 'userName',
            title: '用户名'
        }, {
            field: 'nickname',
            title: '昵称'
        }, {
            field: 'status',
            title: '状态',
            formatter:function(value,row,index){
            	return SysUtil.formatDicItem(value,USERSSTATUS);
            }
        }]
	});
}

/**
 * 分页查询获取查询条件
 * @param params
 * @returns
 */
function getParam(params){
	var selected = zTreeObj.getSelectedNodes();
	if(selected.length<1){
		return false;
	}
	if("root"==selected[0].id){
		return false;
	}
	var filter = new HashMap();
	filter.put("organCode@=",selected[0].data.organCode);
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

function toShowIcon(value,row,index){
	return '<span class="'+value+'"></span>';
}

/**
 * 判断当前机构是否允许创建用户
 * @param level //当前级别
 * @param items //级别关系列表
 * @returns
 */
function havaUser(level,items){
	if("root"==level){
		return false;
	}
	//获取当前级别信息
	var currLevel = null;
	for(var i=0;i<items.length;i++){
		if(items[i].itemCode==level){
			currLevel = items[i];
			break;
		}
	}
	if(currLevel.haveUser=="1"){
		return true;
	}else{
		return false;
	}
}

/**
 * 新增用户
 * @returns
 */
function addUser(){
	var selected = zTreeObj.getSelectedNodes();
	if(selected.length<1){
		PluginUtil.info("请先选择左侧机构树要增加用户的机构");
		return false;
	}
	if(!havaUser(selected[0].data.organLevel,ORGANLEVEL)){
		PluginUtil.info("当前机构不允许创建用户");
		return false;
	}
	$("#userModal").load(basepath+"sys/users/toEditUsers?handle=add&organCode="+selected[0].data.organCode,function(){
		$("#userModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改用户
 * @returns
 */
function editUser(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		PluginUtil.info("请选择一个要编辑的用户");
		return false;
	}
	$("#userModal").load(basepath+"sys/users/toEditUsers?handle=edit&userId="+selected[0].userId,function(){
		$("#userModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 删除用户
 * @returns
 */
function removeUser(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请选择要删除的用户");
		return false;
	}
	PluginUtil.confirm("是否确认删除当前选中的用户？",function(){
		var userCodes = new Array();
 	   $.each(selected,function(index,data){
 		   userCodes.push(data.userCode);
 	   });
 	   PluginUtil.mask("body");
 	   //提交
 	   $.ajax({
 		   url:basepath+'sys/users/deleteUsers',
 		   type:'post',
 		   data:{userCodes:userCodes.join(",")},
 		   success:function(result){
 			   PluginUtil.unmask("body");
 			   if(result.code=='success'){
 				   PluginUtil.info("删除成功");
 				   $('#table').bootstrapTable('refresh');
 			   }else{
 				   PluginUtil.alert(result.data);
 			   }
 		  },
 		  error:function(error){
 			  PluginUtil.unmask("body");
 			  PluginUtil.alert(error);
 		  }
 	   });
	});
}

function userRole(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		PluginUtil.info("请选择一个要分配的用户");
		return false;
	}
	$("#userModal").load(basepath+"sys/users/toUserRole?userCode="+selected[0].userCode,function(){
		$("#userModal").modal({backdrop: 'static', keyboard: false});
	});
}

function showDetail(event, treeId, treeNode){
	$('#show_organCode').html(treeNode.data.organCode);
	$('#show_organName').html(treeNode.data.organName);
	$('#show_organLevel').html(SysUtil.formatDicItem(treeNode.data.organLevel,ORGANLEVEL));
	$('#show_iconUrl').removeClass();
	$('#show_iconUrl').addClass(treeNode.data.iconUrl);
	$('#show_seq').html(treeNode.data.seq);
	$('#table').bootstrapTable('refresh');
}