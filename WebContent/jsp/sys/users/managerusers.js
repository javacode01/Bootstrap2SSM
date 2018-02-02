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
	$('#tree').treeview({
		data : getTree(),
		backColor:'#fff',//设置所有列表树节点的背景颜色。
		borderColor:'#fff',//设置列表树容器的边框颜色，如果不想要边框可以设置showBorder属性为false。
		checkedIcon:'glyphicon glyphicon-check',//设置处于checked状态的复选框图标。
		collapseIcon:'glyphicon glyphicon-minus',//设置列表树可收缩节点的图标。
		color:'#555',//设置列表树所有节点的前景色。
		expandIcon:'glyphicon glyphicon-plus',//设置列表树可展开节点的图标。
		highlightSelected:true,//当选择节点时是否高亮显示。
		onhoverColor:'#F5F5F5',//设置列表树的节点在用户鼠标滑过时的背景颜色。
		levels:0,//Default: 2 设置继承树默认展开的级别。
		showBorder:false,//是否在节点上显示边框。
		onNodeExpanded:function(event,data){
			var param = data.id
			if(data.nodes.length<=0){
				$.ajax({
					type:"post",
					async:false,
					url:basepath+"sys/organ/getOrganNodes",
					dataType:'json',
					data: {organCode:param},
					success:function(result){
						if(undefined!=result&&null!=result){
							for(var i=0;i<result.length;i++){
								var temp = result[i];
								$("#tree").treeview("addNode",[data.nodeId,{node:temp,silent:true}]);
							}
						}
					}
				});
			}
		},
		onNodeSelected:function(event,data){
			$('#show_organCode').html(data.data.organCode);
			$('#show_organName').html(data.data.organName);
			$('#show_organLevel').html(SysUtil.formatDicItem(data.data.organLevel,ORGANLEVEL));
			$('#show_iconUrl').removeClass();
			$('#show_iconUrl').addClass(data.data.iconUrl);
			$('#show_organType').html(SysUtil.formatDicItem(data.data.organType,ORGANTYPE));
			$('#show_seq').html(data.data.seq);
			$('#table').bootstrapTable('refresh');
		}
	});
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
            title: '用户编码',
            width:"20%"
        }, {
            field: 'userName',
            title: '用户名',
            width:"20%"
        }, {
            field: 'nickname',
            title: '昵称',
            width:"20%"
        }, {
            field: 'status',
            title: '状态',
            width:"10%"
        }, {
            field: 'organCode',
            title: '所属组织机构',
            width:"20%"
        }]
	});
}

/**
 * 分页查询获取查询条件
 * @param params
 * @returns
 */
function getParam(params){
	var selected = $("#tree").treeview('getSelected');
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
 * 初始化功能树
 * @returns
 */
function getTree(){
	//根节点
	var root = [{
		  id:"root",
		  text: "组织结构",
		  icon: "glyphicon glyphicon-stop",
		  selectedIcon: "glyphicon glyphicon-stop",
		  state: {
		    expanded: false
		  },
		  nodes:[],
		  data:{
			  organCode:"root",
			  organName:"根节点",
			  organLevel:"1",
			  iconUrl:"glyphicon glyphicon-stop",
			  seq:""
		  }}];
	return root;
}

/**
 * 新增用户
 * @returns
 */
function addUser(){
	var selected = $("#tree").treeview('getSelected');
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择左侧机构树要增加用户的机构"
			});
		return false;
	}
	if('root'==selected[0].id){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "根节点不能增加用户"
			});
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
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要编辑的用户"
			});
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
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择要删除的用户"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的用户？",
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
	    	   var userCodes = new Array();
	    	   $.each(selected,function(index,data){
	    		   userCodes.push(data.userCode);
	    	   })
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/users/deleteUsers',
	    		   type:'post',
	    		   data:{userCodes:userCodes.join(",")},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "删除成功"
	    						});
	    				   $('#table').bootstrapTable('refresh');
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
						});
	    		  }
	    	   });
	       }
	    }
	});
}

function userRole(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一个要分配的用户"
			});
		return false;
	}
	$("#userModal").load(basepath+"sys/users/toUserRole?userCode="+selected[0].userCode,function(){
		$("#userModal").modal({backdrop: 'static', keyboard: false});
	});
}