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
 * 初始化字典树
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
					url:basepath+"sys/dictionaries/getDictionariesNodes",
					dataType:'json',
					data: {functionCode:param},
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
			$('#table').bootstrapTable('destroy');
			initTable();
			
		}
	});
	$('#tree').treeview('expandAll',{levels:2});
}

/**
 * 初始化表格
 * @returns
 */
function initTable(){
	$('#table').bootstrapTable({
		url:basepath+"sys/dictionariesitem/listDictionariesItemByPage",
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
        uniqueId: "recid",                     //每一行的唯一标识，一般为主键列
        toolbar: "#toolbar",                //工具按钮用哪个容器
        showColumns:true,
        showRefresh:true,
        showToggle:true,
        columns: [{
        	checkbox:true
        }, {
            field: 'dicType',
            title: '字典类型',
            width:"20%"
        }, {
            field: 'itemCode',
            title: '字典项编码',
            width:"20%"
        }, {
            field: 'itemName',
            title: '字典项名称',
            width:"20%"
        }, {
            field: 'note',
            title: '备注',
            width:"30%"
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
	filter.put("dicType@=",selected[0].data.dicType);
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 字典新增
 * @returns
 */
function addDic(){
	$("#dicEdit").load(basepath+"sys/dictionaries/toEditDic?handle=add",function(){
		$("#dicEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典修改
 * @returns
 */
function editDic(){
	var selected = $("#tree").treeview('getSelected');
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要编辑的字典"
			});
		return false;
	}
	if('root'==selected[0].id){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "根节点不能编辑"
			});
		return false;
	}
	$("#dicEdit").load(basepath+"sys/dictionaries/toEditDic?handle=edit&recid="+selected[0].id+"&nodeId="+selected[0].nodeId,function(){
		$("#dicEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典删除
 * @returns
 */
function removeDic(){
	var selected = $("#tree").treeview('getSelected');
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择要删除的字典"
			});
		return false;
	}
	if('root'==selected[0].id){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "根节点不能删除"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的字典？",
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
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/dictionaries/deleteDic',
	    		   type:'post',
	    		   data:{recid:selected[0].id},
	    		   success:function(result){
	    			   if(result.code=='success'){
	    				   bootbox.alert({ 
	    						  size: "small",
	    						  title: "提示框",
	    						  message: "删除成功"
	    						});
	    				   treeReload();
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

/**
 * 字典项新增
 * @returns
 */
function addDicItem(){
	var selected = $("#tree").treeview('getSelected');
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请先选择左侧字典树要增加字典项的字典"
			});
		return false;
	}
	if('root'==selected[0].id){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "根节点不能增加字典项"
			});
		return false;
	}
	$("#dicItemEdit").load(basepath+"sys/dictionariesitem/toEditDicItem?handle=add",function(){
		$("#dicItemEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典项修改
 * @returns
 */
function editDicItem(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择一条要编辑的字典项"
			});
		return false;
	}
	$("#dicItemEdit").load(basepath+"sys/dictionariesitem/toEditDicItem?handle=edit&recid="+selected[0].recid,function(){
		$("#dicItemEdit").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 字典项删除
 * @returns
 */
function removeDicItem(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		bootbox.alert({ 
			  size: "small",
			  title: "提示框",
			  message: "请选择要删除的字典项"
			});
		return false;
	}
	bootbox.confirm({
		title:"确认框",
	    message: "是否确认删除当前选中的字典？",
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
	    	   var recids = new Array();
	    	   $.each(selected,function(index,data){
	    		   recids.push(data.recid);
	    	   })
	    	   //提交
	    	   $.ajax({
	    		   url:basepath+'sys/dictionariesitem/deleteDicItems',
	    		   type:'post',
	    		   data:{recids:recids.join(",")},
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

/**
 * 重新加载字典树
 * @returns
 */
function treeReload(){
	$('#tree').treeview("remove");
	initTree();
}

/**
 * 初始化功能树
 * @returns
 */
function getTree(){
	//根节点
	var root = [{
		  id:"root",
		  text: "字典树根节点",
		  icon: "glyphicon glyphicon-stop",
		  selectedIcon: "glyphicon glyphicon-stop",
		  state: {
		    expanded: false
		  },
		  nodes:[],
		  data:{
			  dicType:"",
		  }}];
	return root;
}