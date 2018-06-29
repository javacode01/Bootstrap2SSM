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
	$('#table').bootstrapTable({
		url:basepath+"module/demoorder/listDemoOrderByPage",
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
        }, 
        {
        	field:'recid',
        	title:'订单id'
        },
        {
        	field:'orderNo',
        	title:'订单编号'
        },
        {
        	field:'description',
        	title:'订单简介'
        },
        {
        	field:'creater',
        	title:'创建人'
        },
        {
        	field:'createTime',
        	title:'创建时间'
        },
        {
        	field:'updater',
        	title:'更新人'
        },
        {
        	field:'updateTime',
        	title:'更新时间'
        },
		{
			title:'操作'
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
    var searchrecid = $("#search_recid").val();
	if(searchrecid){
		filter.put("recid@=",searchrecid);
	}
    var searchorderNo = $("#search_orderNo").val();
	if(searchorderNo){
		filter.put("orderNo@=",searchorderNo);
	}
    var searchdescription = $("#search_description").val();
	if(searchdescription){
		filter.put("description@=",searchdescription);
	}
    var searchcreater = $("#search_creater").val();
	if(searchcreater){
		filter.put("creater@=",searchcreater);
	}
    var searchcreateTime = $("#search_createTime").val();
	if(searchcreateTime){
		filter.put("createTime@=",searchcreateTime);
	}
    var searchupdater = $("#search_updater").val();
	if(searchupdater){
		filter.put("updater@=",searchupdater);
	}
    var searchupdateTime = $("#search_updateTime").val();
	if(searchupdateTime){
		filter.put("updateTime@=",searchupdateTime);
	}
	$.extend(params,{filter:filter.getJSON()});
	return params;
}

/**
 * 查询
 * @returns
 */
function search(){
	$('#table').bootstrapTable('destroy');
	initTable();
}

/**
 * 新增
 * @returns
 */
function add(){
	$("#editModal").load(basepath+"module/demoorder/toEditDemoOrder?handle=add",function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 修改
 * @returns
 */
function edit(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length!=1){
		PluginUtil.info("请选择一个要编辑的记录");
		return false;
	}
	$("#editModal").load(basepath+"module/demoorder/toEditDemoOrder?handle=edit&recid="+selected[0].recid,function(){
		$("#editModal").modal({backdrop: 'static', keyboard: false});
	});
}

/**
 * 删除
 * @returns
 */
function remove(){
	var selected = $("#table").bootstrapTable("getSelections");
	if(selected.length<1){
		PluginUtil.info("请先选择要删除的记录");
		return false;
	}
	PluginUtil.confirm("是否确认删除当前选中的记录？",function(){
		var recids = new Array();
	   	$.each(selected,function(index,data){
			recids.push(data.recid);
	   	});
	   	PluginUtil.mask("body");
	   	//提交
	   	$.ajax({
		   	url:basepath+'module/demoorder/deleteDemoOrder',
		   	type:'post',
		   	data:{recids:recids.join(",")},
		   	success:function(result){
		   		PluginUtil.unmask("body");
		    	if(result.code=='success'){
					PluginUtil.info("删除成功");
				   	search();
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